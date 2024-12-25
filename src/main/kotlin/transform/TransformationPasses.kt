package transform

import ir.statement.Program
import transform.constant_propegation.ConstantPropagation
import transform.restructure.*

object TransformationPasses {
    fun apply(program: Program){
        val passes = listOf<Transformer>(
            SkipNoLoopFunctions(),
            ConstantPropagation(),
            ConditionRestructure(),
            ConditionalLoopRestructure(),
            IncrementRestructure(),
            RangeLoopRestructure(),
            SkipNoRangeLoopFunctions(),
            LoopNormalization(),
            ParallelForAnnotator(),
            ProfitabilityAnalysis(),
            LocalsTransferInAnnotator(),
            MarkLoopCountersAsPrivate(),
            MarkLoopTemporariesAsPrivate(),
            //LocalizeRangeLoopCounter(),
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}