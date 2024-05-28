package optimization

import analysis.dfa.Dfa
import ir.finder.SourceMapBuilder
import ir.statement.Assignable
import ir.statement.Assignee
import ir.statement.Function
import ir.statement.Program

// Replace Assignable with const equivalent
class ConstantPropagation : Optimizer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { it.instructions.isNotEmpty() }
            .forEach { function ->
                val srcMap = SourceMapBuilder().also { it.visit(function) {} }.result()
                val dfa = Dfa.from(function)

                dfa.pass { node ->
                    val stmt = node.statement
                    if (stmt != null && stmt is Assignee) {
                        val repl = srcMap[node.statement.id]
                        val src = repl!!.statement as Assignee
                        // TODO: propagate constants using node.IN
                        println(src.assignedWith())
                    }
                }

            }
    }
}