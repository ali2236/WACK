package transform

import ir.annotations.Skip
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.Finders
import ir.statement.*
import ir.statement.Function

// add skip annotation to functions:
// - without range loops
// add skip annotations for loops with function dependencies
// - or with symbol self-dependence
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
                } else {
                    for (loop in rangeLoops) {
                        val functionFinder = BreadthFirstExpressionFinder(FunctionCall::class.java)
                        functionFinder.visit(loop.range.from){}
                        functionFinder.visit(loop.range.to){}
                        functionFinder.visit(loop){}
                        if (functionFinder.result().isNotEmpty()){
                            loop.annotations.add(Skip())
                            continue
                        }

                        // check for symbol self-dependence
                        if (Finders.symbols(loop.range.from).toSet().contains(loop.symbol)){
                            loop.annotations.add(Skip())
                            continue
                        }
                    }
                }
            }
    }

}