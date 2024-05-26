package analysis.dfa

import analysis.cfg.CFG
import analysis.cfg.CfgEdge
import ir.statement.Assignee
import ir.statement.Function
import java.util.Collections

// flow sensitive DFA
class DfaBuilder(val function: Function, val cfg: CFG) {

    private val nodes: MutableList<DfaNode> = mutableListOf()

    fun build(): Dfa {
        initializeDfaFromCFG()
        /*do {
            val changed = runPass()
        } while (changed)*/

        return Dfa(nodes)
    }

    private fun initializeDfaFromCFG() {
        mapNodesFromCFG()
        initializeGEN()
    }

    private fun runPass(): Boolean {
        // forward analysis
        var changed = false
        val q = mutableListOf<DfaNode>(nodes.first())
        val visited = BooleanArray(nodes.size)
        // TODO: don't visit twice / prevent loops
        while (q.isNotEmpty()) {
            val node = q.removeFirst()

            // check if visited
            if (visited[node.id]) {
                continue
            }
            visited[node.id] = true

            // propagate IN -> OUT
            node.IN.forEach {
                node.OUT.put(it)
            }

            // propagate GEN -> OUT
            node.GEN.forEach {
                node.OUT.put(it)
            }

            changed = true


            for (suc in node.successors) {
                val successor = nodes[suc.target]

                // set successor IN to predecessor OUT
                node.OUT.facts.forEach {
                    successor.IN.add(it)
                }

                // add successors
                q.add(successor)
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
                    block.id,
                    block.label,
                    block.statements.firstOrNull(),
                    successors = block.successors
                )
                nodes.add(node)
            }

            nodes.sortBy { it.id }
        }

    }

    private fun initializeGEN() {
        for (block in nodes) {
            if (block.statement != null) {
                val stmt = block.statement
                if (stmt is Assignee) {
                    val fact = DfaFact(
                        symbol = stmt.assignedTo(),
                        value = DfaValue.Expr(stmt.assignedWith())
                    )
                    block.GEN.add(fact)
                }
            }
        }
    }
}