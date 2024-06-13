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
        // propagate IN -> OUT except those in GEN
        node.IN.facts.filter { inIt ->
            !node.GEN.any { genIt ->
                if (genIt.symbol is Load) {
                    try {
                        val address = explainExpression(genIt.symbol.address.clone(), node.IN.facts)
                        if (address is DfaValue.Expr) {
                            val gen = genIt.symbol.clone().also { it.address = address.value }
                            val eq = gen == inIt.symbol
                            return@any eq
                        }
                    } catch (e: Exception) {
                    }
                }
                val eq = genIt.symbol == inIt.symbol
                eq
            }
        }.forEach {
            val o = node.OUT.put(it)
            changed = o || changed
        }

        // propagate GEN -> OUT override Symbol From IN
        node.GEN.forEach { gen ->
            try {
                val fact = explainFact(gen.clone(), node.IN.facts)
                changed = node.OUT.put(fact) || changed
            } catch (e: Exception) {
                // dont add
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
        function.functionData.locals.forEachIndexed { index, localType ->
            start.IN.put(
                DfaFact(
                    Symbol(WasmScope.local, localType, Index(index)),
                    DfaValue.Expr(Value(localType, localType.defaultValue()))
                )
            )
        }
    }

    private fun initializeGEN(dfa: Dfa) {
        for (block in dfa.nodes) {
            if (block.statement != null) {
                val stmt = block.statement
                when (stmt) {
                    is AssignmentStore -> {
                        val fact = DfaFact(
                            symbol = stmt.assignedTo().clone() as SymbolLoad,
                            value = DfaValue.Expr(stmt.assignedWith().clone())
                        )
                        block.GEN.add(fact)
                    }

                    is RangeLoop -> {
                        val fact = DfaFact(stmt.symbol, stmt.range)
                        block.GEN.add(fact)
                        if(block.next != null){ // kina works
                            dfa.nodes[block.next].GEN.add(DfaFact(stmt.symbol, DfaValue.Expr(stmt.range.to)))
                        }
                    }
                }
            }
        }
    }

    private fun explainFact(gen: DfaFact, dfaFacts: Set<DfaFact>): DfaFact {
        if (gen.symbol is Load) {
            val expl = explainExpression(gen.symbol.address, dfaFacts)
            if (expl is DfaValue.Expr) {

                gen.symbol.address = expl.value
            } else {
                throw java.lang.Exception()
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
                        return DfaValue.Undeclared()
                    }
                } else {
                    return DfaValue.Undeclared()
                }
            }

            is BinaryOP -> {
                try {
                    val result = evalBinaryOp(expr, dfaFacts)
                    return DfaValue.Expr(result)
                } catch (e: ExpressionViolation) {
                    return e.left.join(e.right)
                }
            }

            is UnaryOP -> {
                try {
                    val result = evalUnaryOp(expr, dfaFacts)
                    return DfaValue.Expr(result)
                } catch (e: java.lang.Exception) {
                    return DfaValue.Unknown()
                }
            }

            is TeeValue -> {
                return explainExpression(expr.expr, dfaFacts)
            }

            is FunctionCall, is BlockResult -> {
                return DfaValue.Unknown()
            }

            is Select -> {
                val selector = explainExpression(expr.selector, dfaFacts)
                if (selector is DfaValue.Expr && selector.value is Value) {
                    val value = selector.value.value.toInt()
                    val res = if (value == 0) expr.val1 else expr.val2
                    return explainExpression(res, dfaFacts)
                } else {
                    return DfaValue.Unknown()
                }
            }

            is Convert -> {
                val v = explainExpression(expr.value, dfaFacts)
                if (v is DfaValue.Expr) {
                    return DfaValue.Expr(Value(expr.toType, v.value.toString()))
                } else {
                    return DfaValue.Unknown()
                }
            }

            else -> {
                throw Error("Unknown Type $expr")
            }
        }
    }

    fun evalBinaryOp(op: BinaryOP, facts: Set<DfaFact>): Expression {
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
            when (op.type) {
                WasmValueType.i32, WasmValueType.i64 -> {
                    // calculate
                    val l = (leftL.value as Value).value.toLong()
                    val r = (rightL.value as Value).value.toLong()
                    val value : Number = when (op.operator.sign) {
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
                    return Value(op.type, value.toString())
                }

                WasmValueType.f32, WasmValueType.f64 -> {
                    // calculate
                    val l = (leftL.value as Value).value.toDouble()
                    val r = (rightL.value as Value).value.toDouble()
                    val value : Number = when (op.operator.sign) {
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
                    return Value(op.type, value.toString())
                }

                else -> throw Error()
            }
        } else {
            throw ExpressionViolation(leftL, rightL)
        }
    }

    fun evalUnaryOp(op: UnaryOP, facts: Set<DfaFact>): Expression {
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
    if (true){
        return 1
    } else {
        return 0
    }
}
