package transform

import ir.statement.Program
import transform.constant_propegation.ConstantPropagation
import transform.restructure.*

object OptimizationPasses {
    fun apply(program: Program){
        val passes = listOf<Transformer>(
            SkipMarker(),
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