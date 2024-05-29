package ir.finder

import analysis.dfa.DfaBuilder
import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Load
import ir.statement.*

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

            is BinaryOP -> {
                try {
                    val r = DfaBuilder.evalBinaryOp(v, facts)
                    replace(r)
                } catch (e: Exception) {
                    // dont replace
                }
            }

            is AssignmentStore -> {
                // if right is load or binop with load
                var expr: Expression = v.assignedWith()
                try {
                    val r = DfaBuilder.explainExpression(expr, facts)
                    expr = if (r is DfaValue.Expr) r.value else expr
                } catch (e: Exception) {
                    // dont replace
                }
                // if left is store propagate to address
                if (v is Store) {
                    val r = DfaBuilder.explainExpression(v.symbol, facts)
                    val load = if (r is DfaValue.Expr) r.value as Load else v.symbol

                    //
                    // Replace
                    //
                    val replacement = Store(load, expr)
                    replace(replacement)
                }
                return
            }
            else -> super.visit(v, replace)
        }
    }
}