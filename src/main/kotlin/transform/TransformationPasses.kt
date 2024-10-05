package transform

import ir.statement.Program
import transform.constant_propegation.ConstantPropagation
import transform.restructure.*

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