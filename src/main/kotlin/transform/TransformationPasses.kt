package transform

import compiler.WAPC
import ir.statement.Program
import transform.constant_propegation.ConstantPropagation
import transform.restructure.*

object TransformationPasses {
    fun apply(program: Program){
        if(!WAPC.params.multipleMemories){
            OffsetMemoryLocations().apply(program)
        }
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
            ReductionAnnotator(),
            TransferOutAnnotator(),
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