package restructure

import ir.statement.Program

object RestructurePasses {

/*    fun all(program: Program) {
        val passes = listOf(
            // // ConditionalLoopRestructure(),
            // Unneeded: // LoopMemoryCounterAlias(),
            // IncrementRestructure(),
            // RangeLoopRestructure(),
            // // ShiftToMultiply(),
            // RangeLoopRangeRefinement(),
            // Unneeded: // LoopVariableFlattening(),
        )
        passes.forEach { it.run(program) }
    }*/

    fun apply(program: Program) {
        val passes = listOf(
            ConditionalLoopRestructure(),
            ShiftToMultiply(),
        )
        passes.forEach { it.run(program) }
    }
}