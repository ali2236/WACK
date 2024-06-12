package optimization

import analysis.ddt.DependenceTester
import ir.annotations.For
import ir.annotations.Parallel
import ir.statement.Function
import ir.statement.Program

class ParallelForAnnotator : Optimizer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        val loops = DependenceTester.testLoops(function)
        for (loop in loops) {
            loop.annotations.add(Parallel())
            loop.annotations.add(For())
        }
    }
}