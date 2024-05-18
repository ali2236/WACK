package optimization

import ir.statement.Program

object OptimizationPasses {
    fun apply(program: Program){
        val passes = listOf(
            KernelExtraction()
        )
        for (pass in passes){
            pass.apply(program)
        }
    }
}