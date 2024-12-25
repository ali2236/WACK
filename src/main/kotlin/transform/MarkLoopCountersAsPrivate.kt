package transform

import compiler.WAPC
import ir.annotations.For
import ir.annotations.Private
import ir.annotations.Skip
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ExpressionFinder
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop

class MarkLoopCountersAsPrivate : Transformer {
    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                // find all top level for loops
                val topLevelForLoops =
                    BreadthFirstExpressionFinder(RangeLoop::class.java, !WAPC.params.parallelizeInnerLoops)
                        .also { function.visit(it) }
                        .result()
                        .filter { it.hasAnnotation(For::class.java) }

                // mark each sub range loop symbol as private
                // then propagate to top level loop
                for (forLoop in topLevelForLoops) {
                    val subLoops = ExpressionFinder(RangeLoop::class.java)
                        .also { it.visit(forLoop) {} }
                        .result()
                    for (subLoop in subLoops) {
                        forLoop.annotations.add(Private(subLoop.symbol))
                    }
                }
            }
    }
}