package refinment

import ir.statement.Program

object RefinerPasses {

    fun all(program: Program){
        val passes = listOf(
            // MEMORY
            ShiftToMultiply(),
            MemoryVariableRecovery(),
            // LOOP
            IncrementRefiner(),
            WhileLoopRefiner(),
            ForLoopRefiner(),
            ForLoopRangeRefinement()
            // TODO: have typed memory
        )
        passes.forEach { it.run(program) }
    }
}