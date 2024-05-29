package optimization

import analysis.dfa.Dfa
import analysis.dfa.DfaBuilder
import analysis.dfa.DfaValue
import ir.expression.Expression
import ir.expression.Load
import ir.finder.SourceMapBuilder
import ir.statement.*
import ir.statement.Function

// Replace Assignable with const equivalent
class ConstantPropagation : Optimizer {
    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>().filter { it.instructions.isNotEmpty() }.forEach { function ->
                val srcMap = SourceMapBuilder().also { it.visit(function) {} }.result()
                val dfa = Dfa.from(function)

                dfa.pass { node ->
                    val stmt = node.statement
                    if (stmt != null && stmt is Assignee) {
                        val repl = srcMap[node.statement.id]
                        val src = repl!!.statement as Assignee
                        // TODO: propagate constants using node.IN
                        // if right is load or binop with load
                        var expr: Expression = stmt.assignedWith()
                        try {
                            val r = DfaBuilder.explainExpression(expr, node.IN.facts)
                            expr = if (r is DfaValue.Expr) r.value else expr
                        } catch (e: Exception) {
                            // dont replace
                        }
                        // if left is store propagate to address
                        if (stmt is Store) {
                            val r = DfaBuilder.explainExpression(stmt.symbol, node.IN.facts)
                            val load = if (r is DfaValue.Expr) r.value as Load else stmt.symbol

                            //
                            // Replace
                            //
                            val replacement = Store(load, expr)
                            repl.replace(replacement)
                        }
                    }
                }

            }
    }
}