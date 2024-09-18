package transform

import analysis.ddt.DependenceTester
import ir.annotations.For
import ir.annotations.OnlyIf
import ir.annotations.Parallel
import ir.annotations.Skip
import ir.statement.Function
import ir.statement.Program

class ParallelForAnnotator : Transformer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        val loops = DependenceTester.testLoops(function)
        for (parallelLoop in loops) {
            val loop = parallelLoop.loop
            loop.annotations.add(Parallel())
            loop.annotations.add(For())
            println("Loop Marked as Parallel For")
            if(parallelLoop.conditions.isNotEmpty()){
                for (condition in parallelLoop.conditions){
                    loop.annotations.add(OnlyIf(condition))
                }
                println("Added Conditions To Parallel For Loop")
            }
        }
    }
}