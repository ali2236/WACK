package restructure

import ir.statement.Program

object RefinerPasses {

    fun all(program: Program) {
        val passes = listOf(
            WhileLoopRestructure(),
            LoopMemoryCounterAlias(),
            IncrementRestructure(),
            ForLoopRestructure(),
            ShiftToMultiply(),
            ForLoopRangeRefinement(),
            LoopVariableFlattening(),
        )
        passes.forEach { it.run(program) }
    }
}