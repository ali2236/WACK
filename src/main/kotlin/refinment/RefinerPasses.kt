package refinment

import ir.statement.Program

object RefinerPasses {

    fun all(program: Program) {
        val passes = listOf(
            WhileLoopRefiner(),
            LoopMemoryCounterAlias(),
            IncrementRefiner(),
            ForLoopRefiner(),
            ShiftToMultiply(),
            ForLoopRangeRefinement(),
            LoopVariableFlattening(),
        )
        passes.forEach { it.run(program) }
    }
}