package optimization

import ir.statement.Program

object OptimizationPasses {
    fun apply(program: Program){
        val passes = listOf<Optimizer>(
            ParallelForAnnotator(),
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}