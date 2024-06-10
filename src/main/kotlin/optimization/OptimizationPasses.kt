package optimization

import ir.statement.Program
import ir.statement.RangeLoop
import restructure.*

object OptimizationPasses {
    fun apply(program: Program){
        val passes = listOf<Optimizer>(
            ShiftToMultiply(),
            ConstantPropagation(),
            AliasMemory(),
            ConditionRestructure(),
            ConditionalLoopRestructure(),
            IncrementRestructure(),
            RangeLoopRestructure(),
            // ParallelForAnnotator(),
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}