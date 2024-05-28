package analysis.dfa

import analysis.cfg.CFG
import analysis.cfg.CfgEdge
import ir.expression.*
import ir.statement.Assignable
import ir.statement.Assignee
import ir.statement.Function
import wasm.Index
import wasm.WasmScope
import wasm.WasmValueType

// flow sensitive DFA
class DfaBuilder(val function: Function, val cfg: CFG) {

    private val nodes: MutableList<DfaNode> = mutableListOf()

    fun build(): Dfa {
        initializeDfaFromCFG()
        var propagated = false
        while (!propagated) {
            val changed = runPass()
            propagated = !changed
        }

        return Dfa(nodes)
    }

    private fun initializeDfaFromCFG() {
        mapNodesFromCFG()
        addFunctionLocals()
        initializeGEN()
    }

    private fun runPass(): Boolean {
        // forward analysis
        var changed = false
        val q = mutableListOf(nodes.first())
        val visited = BooleanArray(nodes.size)
        while (q.isNotEmpty()) {
            val node = q.removeFirst()

            // check if visited
            if (visited[node.id]) {
                continue
            }
            visited[node.id] = true

            changed = propegate(node) || changed


            for (suc in node.successors) {
                val successor = nodes[suc.target]

                // set successor IN to predecessor OUT
                node.OUT.facts.forEach { fact ->
                    val t = successor.IN.put(fact)
                    changed = t || changed
                }

                // add successors
                q.add(successor)
            }
        }

        return changed
    }

    // returns changed
    private fun propegate(node: DfaNode): Boolean{
        var changed = false
        // propagate IN -> OUT except those in GEN
        node.IN.facts.filter { inIt -> !node.GEN.any { genIt -> genIt.symbol == inIt.symbol } }.forEach {
            changed = node.OUT.put(it) || changed
        }

        // propagate GEN -> OUT override Symbol From IN
        node.GEN.forEach { gen ->
            try {
                val fact = explainFact(gen, node.IN.facts)
                changed = node.OUT.put(fact) || changed
            } catch (e: Exception) {
                // dont add
                println("didn't add $gen")
            }
        }

        return changed
    }

    private fun mapNodesFromCFG() {
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
                nodes.add(firstStmt)

                // then statement 1 to n-1
                for (i in 1 until blocksNeeded) {
                    val stmtNode = DfaNode(
                        id = idBase++,
                        label = null,
                        statement = block.statements[i],
                        successors = mutableListOf(CfgEdge(null, idBase)),
                    )
                    nodes.add(stmtNode)
                }
                // then last carries block successors
                val lastStmtNode = DfaNode(
                    id = idBase++,
                    label = null,
                    statement = block.statements.last(),
                )
                nodes.add(lastStmtNode)
                lastStmtNode.successors.addAll(block.successors)
            } else {
                val node = DfaNode(
                    block.id, block.label, block.statements.firstOrNull(), successors = block.successors
                )
                nodes.add(node)
            }

            nodes.sortBy { it.id }
        }

    }

    private fun addFunctionLocals() {
        val start = nodes.first()
        function.functionData.locals.forEachIndexed { index, localType ->
            start.IN.put(
                DfaFact(
                    Symbol(WasmScope.local, localType, Index(index)),
                    DfaValue.Expr(Value(localType, localType.defaultValue()))
                )
            )
        }
    }

    private fun initializeGEN() {
        for (block in nodes) {
            if (block.statement != null) {
                val stmt = block.statement
                if (stmt is Assignee) {
                    val fact = DfaFact(
                        symbol = stmt.assignedTo(), value = DfaValue.Expr(stmt.assignedWith())
                    )
                    block.GEN.add(fact)
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

    private fun explainExpression(expr: Expression, dfaFacts: Set<DfaFact>): DfaValue {
        when (expr) {
            is Value -> {
                // const
                return DfaValue.Expr(expr)
            }

            is Assignable -> {
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

            is TeeValue -> {
                return explainExpression(expr.expr, dfaFacts)
            }

            else -> {
                throw Error("Unknown Type $expr")
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
            when (op.type) {
                WasmValueType.i32, WasmValueType.i64 -> {
                    // calculate
                    val l = (leftL.value as Value).value.toLong()
                    val r = (rightL.value as Value).value.toLong()
                    val value = when (op.operator.sign) {
                        "+" -> l + r
                        "-" -> l - r
                        "*" -> l * r
                        "/" -> l / r
                        "&" -> l and r
                        "|" -> l or r
                        "==" -> l == r
                        "!=" -> l != r
                        "<" -> l < r
                        "<=" -> l <= r
                        ">" -> l > r
                        ">=" -> l >= r
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
                    val value = when (op.operator.sign) {
                        "+" -> l + r
                        "-" -> l - r
                        "*" -> l * r
                        "/" -> l / r
                        "==" -> l == r
                        "!=" -> l != r
                        "<" -> l < r
                        "<=" -> l <= r
                        ">" -> l > r
                        ">=" -> l >= r
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

    class ExpressionViolation(val left: DfaValue, val right: DfaValue) : Exception()
}