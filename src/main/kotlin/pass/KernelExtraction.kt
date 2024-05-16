package pass

import analysis.ddt.DdgBuilder
import ir.statement.Function
import ir.statement.Program

class KernelExtraction : Pass {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach(::applyToFunction)
    }

    private fun applyToFunction(function: Function){
        val ddt = DdgBuilder(function).build()

        // val loops =
    }
}