package transform

import ir.annotations.Skip
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.*
import ir.statement.Function

// add skip annotation to functions:
// - without a body
// - without loops
class SkipNoLoopFunctions : Transformer {

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .forEach { function ->
                val loops = BreadthFirstExpressionFinder(Loop::class.java, true).also { function.visit(it) }.result()
                if(loops.isEmpty()){
                    function.annotations.add(Skip())
                } else {
                    for (loop in loops){
                        if (loop.instructions.any { it is FunctionCall || it is IndirectFunctionCall }){
                            function.annotations.add(Skip())
                        }
                    }
                }
            }
    }

}