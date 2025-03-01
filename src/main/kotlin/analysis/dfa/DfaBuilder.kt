package analysis.dfa

import analysis.cfg.CFG
import analysis.cfg.CfgEdge
import ir.expression.*
import ir.statement.*
import ir.statement.Function
import ir.wasm.Index
import ir.wasm.WasmScope
import ir.wasm.WasmValueType
import kotlin.math.abs

// flow sensitive DFA
object DfaBuilder {

    fun build(function: Function, cfg: CFG): Dfa {
        val dfa = initializeDfaFromCFG(cfg, function)
        var propagated = false
        var i = 0
        while (!propagated) {
            var changed = false
            dfa.pass { node ->
                val p = propegate(node)
                changed = p || changed
                if (i > 100 && p) {
                    println("stuck on node ${node.id} propagation from function ${function.functionData.index}")
                }

                for (suc in node.successors) {
                    val successor = dfa.nodes[suc.target]

                    // set successor IN to predecessor OUT
                    node.OUT.facts.forEach { fact ->
                        val t = successor.IN.put(fact)
                        changed = t || changed
                        if (i > 100 && t) {
                            println("stuck on node ${node.id} propagation from function ${function.functionData.index}")
                        }
                    }
                }
            }
            propagated = !changed
            i++
        }

        return dfa
    }

    private fun initializeDfaFromCFG(cfg: CFG, function: Function): Dfa {
        val dfa = Dfa(mutableListOf())
        mapNodesFromCFG(cfg, dfa)
        addFunctionLocals(dfa, function)
        initializeGEN(dfa)
        return dfa
    }

    // returns changed
    private fun propegate(node: DfaNode): Boolean {
        var changed = false
        // propagate IN -> OUT except those in GEN, those are KILLed
        node.IN.facts.filter { inIt ->
            val gensForIn = node.GEN.facts.filter { genIt ->
                var eq = genIt.symbol == inIt.symbol
                if (genIt.symbol is Load) {
                    try {
                        val address = explainExpression(genIt.symbol.address.clone(), node.IN.facts)
                        if (address is DfaValue.Expr) {
                            val gen = genIt.symbol.clone().also { it.address = address.value }
                            eq = gen == inIt.symbol
                        }
                    } catch (e: Exception) {
                    }
                }
                eq
            }

            // all INs are KILLed
            if (gensForIn.isNotEmpty()){
                node.KILL.put(inIt)
            }

            gensForIn.isEmpty()
        }.forEach {
            val o = node.OUT.put(it)
            changed = o || changed
        }

        // propagate GEN -> OUT override Symbol From IN
        node.GEN.facts.forEach { gen ->
            try {
                val fact = explainFact(gen.clone(), node.IN.facts)
                changed = node.OUT.put(fact) || changed
            } catch (e: Exception) {
                // dont add
                // TODO: when Load address is tee
                // println("didn't add $gen")
            }
        }

        return changed
    }

    private fun mapNodesFromCFG(cfg: CFG, dfa: Dfa) {
        var idBase = cfg.nodes.size
        for (block in cfg.nodes) {
            if (block.statements.size > 1) {
                val blocksNeeded = block.statements.size - 1

                // first is the block itself
                val firstStmt = DfaNode(
                    block.id,
                    block.label,
                    block.statements.firstOrNull(),
                    successors = mutableListOf(CfgEdge(null, idBase))
                )
                dfa.nodes.add(firstStmt)

                // then statement 1 to n-1
                for (i in 1 until blocksNeeded) {
                    val stmtNode = DfaNode(
                        id = idBase++,
                        label = null,
                        statement = block.statements[i],
                        successors = mutableListOf(CfgEdge(null, idBase)),
                    )
                    dfa.nodes.add(stmtNode)
                }
                // then last carries block successors
                val lastStmtNode = DfaNode(
                    id = idBase++,
                    label = null,
                    statement = block.statements.last(),
                )
                dfa.nodes.add(lastStmtNode)
                lastStmtNode.successors.addAll(block.successors)
            } else {
                val node = DfaNode(
                    block.id,
                    block.label,
                    block.statements.firstOrNull(),
                    successors = block.successors,
                    next = block.next?.id
                )
                dfa.nodes.add(node)
            }

            dfa.nodes.sortBy { it.id }
        }

    }

    private fun addFunctionLocals(dfa: Dfa, function: Function) {
        val start = dfa.nodes.first()
        // add function params
        val params = function.functionData.type.params
        for (i in 0 until params.size){
            start.IN.put(
                DfaFact(
                    Symbol(WasmScope.local, params[i], Index.number(i)),
                    DfaValue.Alias(null), // unknown
                )
            )
        }
        // add function locals
        val locals = function.functionData.locals
        for (i in 0 until locals.size){
            val localType = locals[i]
            start.IN.put(
                DfaFact(
                    Symbol(WasmScope.local, localType, Index.number(i+params.size)),
                    DfaValue.Expr(Value(localType, localType.defaultValue()))
                )
            )
        }
        // this was removed because it was wrong?
        /*(function.functionData.type.params + function.functionData.locals).forEachIndexed { index, localType ->
            start.IN.put(
                DfaFact(
                    Symbol(WasmScope.local, localType, Index.number(index)),
                    DfaValue.Expr(Value(localType, localType.defaultValue()))
                )
            )
        }*/
    }

    private fun initializeGEN(dfa: Dfa) {
        for (block in dfa.nodes) {
            if (block.statement != null) {
                val stmt = block.statement
                FactsFinder(dfa, block, stmt)
            }
        }
    }

    private fun explainFact(gen: DfaFact, dfaFacts: Set<DfaFact>): DfaFact {
        if (gen.symbol is Load) {
            val expl = explainExpression(gen.symbol.address, dfaFacts)
            if (expl is DfaValue.Expr) {
                gen.symbol.address = expl.value
            } else if (gen.symbol.address is Symbol) {
                // allowed
            } else {
                // only single symbol addresses are allowed
                throw Exception()
            }
        }
        if (gen.value is DfaValue.Expr) {
            // Evaluate GEN
            return DfaFact(gen.symbol, explainExpression(gen.value.value, dfaFacts))
        } else {
            return gen
        }
    }

    fun explainExpression(expr: Expression, dfaFacts: Set<DfaFact>): DfaValue {
        when (expr) {
            is Value -> {
                // const
                return DfaValue.Expr(expr)
            }

            is SymbolLoad -> {
                val query = dfaFacts.firstOrNull { it.symbol == expr }
                if (query != null) {
                    // variable reference
                    return query.value
                } else if (expr is Load) {
                    val value = explainExpression(expr.address, dfaFacts)
                    if (value is DfaValue.Expr) {
                        expr.address = value.value
                        val query = dfaFacts.firstOrNull { it.symbol == expr }
                        if (query != null) {
                            return query.value
                        }
                        return DfaValue.Expr(expr)
                    } else {
                        return DfaValue.Alias(expr)
                    }
                } else {
                    return DfaValue.Alias(expr)
                }
            }

            is BinaryOP -> {
                try {
                    val result = evalBinaryOp(expr, dfaFacts)
                    return DfaValue.Expr(result)
                } catch (e: ExpressionViolation) {
                    //return e.left.join(e.right)
                    return DfaValue.Alias(expr)
                }
            }

            is UnaryOP -> {
                try {
                    val result = evalUnaryOp(expr, dfaFacts)
                    return DfaValue.Expr(result)
                } catch (e: java.lang.Exception) {
                    return DfaValue.Alias(expr)
                }
            }

            is Tee -> {
                return explainExpression(expr.teeValue(), dfaFacts)
            }

            is FunctionResult, is SingleResultFunction, is ResultBlock, is MemoryGrow, is MemorySize, is CMPXCHG -> {
                return DfaValue.Alias(expr)
            }

            is Select -> {
                val selector = explainExpression(expr.selector, dfaFacts)
                if (selector is DfaValue.Expr && selector.value is Value) {
                    val value = selector.value.value.toInt()
                    val res = if (value == 0) expr.val1 else expr.val2
                    return explainExpression(res, dfaFacts)
                } else {
                    return DfaValue.Alias(expr)
                }
            }

            is Convert -> {
                val v = explainExpression(expr.value, dfaFacts)
                if (v is DfaValue.Expr) {
                    return DfaValue.Expr(Value(expr.toType, v.value.toString()))
                } else {
                    return DfaValue.Alias(expr)
                }
            }

            is StackExpression -> {
                return explainExpression(expr.expr, dfaFacts)
            }

            else -> {
                throw Error("Unknown Type ${expr.javaClass.simpleName}")
            }
        }
    }

    private fun evalBinaryOp(op: BinaryOP, facts: Set<DfaFact>): Expression {
        if (op.left is BinaryOP) {
            op.left = evalBinaryOp(op.left as BinaryOP, facts)
        }

        if (op.right is BinaryOP) {
            op.right = evalBinaryOp(op.right as BinaryOP, facts)
        }

        val leftL: DfaValue = explainExpression(op.left, facts)
        val rightL: DfaValue = explainExpression(op.right, facts)

        if (leftL is DfaValue.Expr && rightL is DfaValue.Expr && leftL.value is Value && rightL.value is Value) {
            // type
            val type = op.exprType()
            when (type) {
                WasmValueType.i32, WasmValueType.i64 -> {
                    // calculate
                    val l = leftL.value.value.toLong()
                    val r = rightL.value.value.toLong()
                    val value: Number = when (op.operator.sign) {
                        "+" -> l + r
                        "-" -> l - r
                        "*" -> l * r
                        "/" -> l / r
                        "&" -> l and r
                        "|" -> l or r
                        "==" -> (l == r).toInt()
                        "!=" -> (l != r).toInt()
                        "<" -> (l < r).toInt()
                        "<=" -> (l <= r).toInt()
                        ">" -> (l > r).toInt()
                        ">=" -> (l >= r).toInt()
                        "<<" -> l shl r.toInt()
                        ">>" -> l ushr r.toInt()
                        "^" -> l xor r
                        else -> throw Error(op.operator.toString() + " not supported!")
                    }
                    return Value(type, value.toString())
                }

                WasmValueType.f32, WasmValueType.f64 -> {
                    // calculate
                    val l = leftL.value.value.toDouble()
                    val r = rightL.value.value.toDouble()
                    val value: Number = when (op.operator.sign) {
                        "+" -> l + r
                        "-" -> l - r
                        "*" -> l * r
                        "/" -> l / r
                        "==" -> (l == r).toInt()
                        "!=" -> (l != r).toInt()
                        "<" -> (l < r).toInt()
                        "<=" -> (l <= r).toInt()
                        ">" -> (l > r).toInt()
                        ">=" -> (l >= r).toInt()
                        else -> throw Error(op.operator.toString() + " not supported!")
                    }
                    return Value(type, value.toString())
                }

                else -> throw Error()
            }
        } else {
            throw ExpressionViolation(leftL, rightL)
        }
    }

    private fun evalUnaryOp(op: UnaryOP, facts: Set<DfaFact>): Expression {
        var value = op.value
        if (op.value !is Value) {
            val fact = explainExpression(op.value, facts)
            if (fact is DfaValue.Expr) {
                value = fact.value
            }
        }

        if (value is Value) {
            when (op.type) {
                WasmValueType.i32, WasmValueType.i64 -> {
                    val v = value.value.toLong()
                    val res = when (op.operator.sign) {
                        "-" -> -v
                        "abs:" -> abs(v)
                        else -> throw Error("${op.operator.sign} not supported!")
                    }
                    return Value(op.type, res.toString())
                }

                WasmValueType.f32, WasmValueType.f64 -> {
                    val v = value.value.toDouble()
                    val res = when (op.operator.sign) {
                        "-" -> -v
                        "abs:" -> abs(v)
                        else -> throw Error("${op.operator.sign} not supported!")
                    }
                    return Value(op.type, res.toString())
                }

                else -> throw Error()
            }
        } else {
            throw java.lang.Exception()
        }
    }

    class ExpressionViolation(val left: DfaValue, val right: DfaValue) : Exception()
}

private fun Boolean.toInt(): Int {
    if (this) {
        return 1
    } else {
        return 0
    }
}
