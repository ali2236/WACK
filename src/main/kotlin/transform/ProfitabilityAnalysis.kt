package transform

import analysis.dfa.Dfa
import compiler.WACK
import compiler.WAPC
import ir.annotations.For
import ir.annotations.Parallel
import ir.annotations.Skip
import ir.annotations.Tasks
import ir.expression.BinaryOP
import ir.finder.CostFinder
import ir.finder.LoopFinder
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop
import ir.wasm.WasmValueType

class ProfitabilityAnalysis : Transformer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        val parallelForLoops = LoopFinder(RangeLoop::class.java, !WAPC.params.parallelizeInnerLoops)
            .also { function.visit(it) }
            .result()
            .map { it.statement }
            .filter { it.hasAnnotation(Parallel::class.java) }

        val finder = Dfa.from(function).finder()
        val costFinder = CostFinder(finder)
        for(loop in parallelForLoops){
            val (cost, symbolicCost) = costFinder.guesstimate(loop)
            if (cost != CostFinder.UNKNOWN){
                if (cost < WAPC.params.minimumLoopCost){
                    // don't parallelize
                    loop.annotations.removeIf { it is Parallel || it is For }
                } else {
                    // calculate optimal number of tasks
                    when(WAPC.params.scheduler){
                        WAPC.Scheduler.thread -> {}
                        WAPC.Scheduler.task -> {
                            loop.annotations.add(
                                Tasks(loop.range.to),
                            )
                        }
                    }
                }
            } else {
                loop.annotations.add(
                    Tasks(loop.range.to),
                )
            }
        }
    }
}