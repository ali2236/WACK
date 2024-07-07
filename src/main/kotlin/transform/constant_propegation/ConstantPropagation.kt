package transform.constant_propegation

import analysis.dfa.Dfa
import ir.statement.*
import ir.statement.Function
import transform.Transformer

// Replace Assignable with const equivalent
class ConstantPropagation : Transformer {
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