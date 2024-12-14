package transform

import ir.annotations.Skip
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.Loop
import ir.statement.Program
import ir.statement.RangeLoop

// add skip annotation to functions:
// - without range loops
class SkipNoRangeLoopFunctions : Transformer {

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                val rangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java, true)
                    .also { function.visit(it) }
                    .result()

                if (rangeLoops.isEmpty()){
                    function.annotations.add(Skip())
                }
            }
    }

}