package transform

import ir.statement.Program
import transform.constant_propegation.ConstantPropagation
import transform.restructure.*
import transform.shift_to_multiply.ShiftToMultiply

object TransformationPasses {
    fun apply(program: Program){
        val passes = listOf<Transformer>(
            SkipMarker(),
            //UnTeeRestructure(),
            ShiftToMultiply(),
            ConstantPropagation(),
            ConditionRestructure(),
            ConditionalLoopRestructure(),
            IncrementRestructure(),
            RangeLoopRestructure(),
            ParallelForAnnotator(),
            StackBaseAliasAnnotator(),
            MarkLoopCountersAsPrivate(),
            //LocalizeRangeLoopCounter(),
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}