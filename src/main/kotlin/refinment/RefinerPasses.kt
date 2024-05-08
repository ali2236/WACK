package refinment

import ast.statement.Program

object RefinerPasses {

    fun all(program: Program){
        val passes = listOf(
            IncrementRefiner(),
            WhileLoopRefiner(),
            ForLoopRefiner(),
        )
        passes.forEach { it.run(program) }
    }
}