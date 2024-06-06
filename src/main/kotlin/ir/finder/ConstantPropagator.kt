package ir.finder

import analysis.dfa.DfaBuilder
import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import ir.expression.*
import ir.statement.*
import wasm.WasmValueType

class ConstantPropagator : Visitor() {

    var facts: Set<DfaFact> = setOf()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is If -> {
                visit(v.condition) {
                    v.condition = it as Expression
                }
            }

            is Block -> {}

            /*is BinaryOP -> {
                try {
                    val r = DfaBuilder.evalBinaryOp(v, facts)
                    replace(r)
                } catch (e: Exception) {
                    // dont replace
                }
            }*/

            is SymbolLoad -> {
                if (v is Load) {
                    // propagate to address if possible
                    v.visit(this)
                }

                // look it up
                val fact = facts.firstOrNull { it.symbol == v }

                // replace if existed
                if (fact != null && fact.value is DfaValue.Expr && fact.value.value is Value) {
                    replace(fact.value.value)
                }
            }

            is Increment -> {
                // ignore
            }

            is AssignmentStore -> {
                ///
                /// Look for symbols and loads on the right side
                ///
                if(v.id == 128L){
                    println()
                }

                visit(v.assignedWith()) { v.replaceAssign(it as Expression) }

                ///
                /// Look for symbols and loads in Store Symbol
                /// If not a store then skip
                ///

                val assignee = v.assignedTo()
                if(assignee is Load){
                    // propagate to address
                    visit(assignee.address){ assignee.address = it as Expression}
                    //assignee.address.visit(this)
                }
                return
            }

            else -> super.visit(v, replace)
        }
    }
}