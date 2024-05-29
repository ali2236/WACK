package optimization

import analysis.dfa.Dfa
import analysis.dfa.DfaBuilder
import analysis.dfa.DfaValue
import ir.expression.Expression
import ir.expression.Load
import ir.finder.ConstantPropagator
import ir.finder.SourceMapBuilder
import ir.statement.*
import ir.statement.Function

// Replace Assignable with const equivalent
class ConstantPropagation : Optimizer {
    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>().filter { it.instructions.isNotEmpty() }.forEach { function ->
                val srcMap = SourceMapBuilder().also { it.visit(function) {} }.result()
                val dfa = Dfa.from(function)
                val propagator = ConstantPropagator()

                dfa.pass { node ->
                    val stmt = node.statement
                    if (stmt != null) {
                        val repl = srcMap[node.statement.id]!!
                        propagator.facts = node.IN.facts
                        propagator.visit(repl.statement, repl.replace)
                    }
                }

            }
    }
}