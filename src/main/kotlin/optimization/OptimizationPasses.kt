package optimization

import generation.wasi.threads.WasiThreadsKernelGenerator
import ir.statement.Program

object OptimizationPasses {
    fun apply(program: Program){
        val passes = listOf<Optimizer>(
            ParallelForAnnotator(),
            WasiThreadsKernelGenerator(),
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}