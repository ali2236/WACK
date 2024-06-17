package optimization

import ir.statement.Program
import optimization.constant_propegation.ConstantPropagation
import optimization.restructure.*
import optimization.restructure.archive.AliasLoopLoadSymbol

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
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}