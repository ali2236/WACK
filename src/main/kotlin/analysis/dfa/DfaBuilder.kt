package analysis.dfa

import analysis.cfg.CFG
import ir.statement.Assignee
import ir.statement.Function
import java.util.Collections

// flow sensitive DFA
class DfaBuilder(val function: Function, val cfg: CFG) {

    private lateinit var nodes: List<DfaNode>

    fun build() : Dfa {
        initializeDfaFromCFG()
        runPass()
        return Dfa(nodes)
    }

    private fun initializeDfaFromCFG() {
        mapNodesFromCFG()
        initializeGEN()
    }

    private fun runPass() {
        // forward analysis
        val q = mutableListOf<DfaNode>(nodes.first())
        // TODO: don't visit twice / prevent loops
        while (q.isNotEmpty()){
            val node = q.removeFirst()
            // propagate IN -> OUT
            node.IN.forEach {
                node.OUT.put(it)
            }

            // propagate GEN -> OUT
            node.GEN.forEach {
                node.OUT.put(it)
            }


            for (suc in node.successors){
                val successor = nodes[suc.target]

                // set successor IN to predecessor OUT
                node.OUT.facts.forEach {
                    successor.IN.add(it)
                }

                // add successors
                q.add(successor)
            }
        }
    }

    private fun mapNodesFromCFG() {
        nodes = cfg.nodes.map {
            DfaNode(
                it.id,
                it.label,
                it.statements,
                successors = it.successors
            )
        }
    }

    private fun initializeGEN() {
        for (block in nodes) {
            for (stmt in block.statements) {
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