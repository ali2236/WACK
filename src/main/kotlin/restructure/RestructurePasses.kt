package restructure

import ir.statement.Program

object RestructurePasses {

    fun all(program: Program) {
        val passes = listOf(
            //ConditionalLoopRestructure(),
            LoopMemoryCounterAlias(),
            IncrementRestructure(),
            //RangeLoopRestructure(),
            // ShiftToMultiply(),
            //RangeLoopRangeRefinement(),
            // LoopVariableFlattening(),
        )
        passes.forEach { it.run(program) }
    }
}