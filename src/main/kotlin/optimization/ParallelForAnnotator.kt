package optimization

import analysis.ddt.DdgBuilder
import ir.annotations.For
import ir.annotations.Parallel
import ir.finder.LoopFinder
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop

class ParallelForAnnotator : Optimizer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach { applyToFunction(program, it) }
    }

    private fun applyToFunction(program: Program, function: Function) {
        val module = program.module
        val ddt = DdgBuilder(function).build()

        val loops = LoopFinder(RangeLoop::class.java, true).apply { visit(function){ } }.result()
        for ((loop, replace) in loops) {
            val loopIsParallelizable = true // TODO: check dependencies
            if (loopIsParallelizable) {
                loop.annotations.add(Parallel())
                loop.annotations.add(For())
            }
        }
    }
}