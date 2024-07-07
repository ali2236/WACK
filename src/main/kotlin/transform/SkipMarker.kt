package transform

import ir.annotations.Skip
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.Finders
import ir.statement.Function
import ir.statement.Loop
import ir.statement.Program

// add skip annotation to functions:
// - without a body
// - without loops
class SkipMarker : Transformer {

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .forEach { function ->
                val loops = BreadthFirstExpressionFinder(Loop::class.java, true).also { function.visit(it) }.result()
                if(loops.isEmpty()){
                    function.annotations.add(Skip())
                }
            }
    }

}