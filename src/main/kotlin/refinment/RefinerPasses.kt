package refinment

import ir.statement.Program

object RefinerPasses {

    fun all(program: Program){
        val passes = listOf(
            // LOOP
            IncrementRefiner(),
            WhileLoopRefiner(),
            ForLoopRefiner(),
            ForLoopRangeRefinement()
            // MEMORY
            // TODO: have typed memory
        )
        passes.forEach { it.run(program) }
    }
}