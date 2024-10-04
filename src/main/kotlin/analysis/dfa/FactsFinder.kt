package analysis.dfa

import ir.expression.BinaryOP
import ir.expression.Value
import ir.finder.Visitor
import ir.statement.*

class FactsFinder(val dfa: Dfa,val block: DfaNode, stmt: Statement) : Visitor() {

    init {
        visit(stmt){}
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is AssignmentStore -> {
                val fact = DfaFact(
                    symbol = v.assignedTo().clone() as SymbolLoad,
                    value = DfaBuilder.explainExpression(v.assignedWith().clone(), setOf())
                )
                block.GEN.put(fact)
            }

            is If -> {
                visit(v.condition){}
                return
            }

            is RangeLoop -> {
                val fact = DfaFact(v.symbol, v.range)
                block.GEN.put(fact)
                // add Range information into data flow analysis
                if (block.next != null) {
                    // range.to can be a non compile time constant
                    val dfaValue = when(v.range.to){
                        is Value -> DfaValue.Expr(v.range.to)
                        else -> DfaValue.Alias(v.range.to)
                    }
                    // TODO: check if the variable is not reassigned in that block
                    val target = dfa.nodes[block.next]
                    val symbolReAssigned = target.GEN.facts.any { it.symbol == v.symbol }
                    if (!symbolReAssigned){
                        target.GEN.put(DfaFact(v.symbol, dfaValue))
                    }
                }
                return
            }

            is Block -> {
                return
            }
        }
        super.visit(v, replace)
    }
}