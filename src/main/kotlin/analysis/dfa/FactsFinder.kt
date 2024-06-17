package analysis.dfa

import ir.expression.BinaryOP
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
                if (block.next != null) { // kina works
                    dfa.nodes[block.next].GEN.put(DfaFact(v.symbol, DfaValue.Expr(v.range.to)))
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