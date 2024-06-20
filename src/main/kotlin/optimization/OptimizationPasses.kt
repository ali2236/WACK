package optimization

import ir.statement.Program
import optimization.constant_propegation.ConstantPropagation
import optimization.restructure.*

object OptimizationPasses {
    fun apply(program: Program){
        val passes = listOf<Optimizer>(
            ShiftToMultiply(),
            ConstantPropagation(),
            ConditionRestructure(),
            ConditionalLoopRestructure(),
            IncrementRestructure(),
            RangeLoopRestructure(),
            ParallelForAnnotator(),
            MarkLoopCountersAsPrivate(),
            //LocalizeRangeLoopCounter(),
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}