package transform

import analysis.ddt.DependenceTester
import ir.annotations.*
import ir.expression.BinaryOP
import ir.expression.Load
import ir.finder.Finders
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
            if(parallelLoop.conditions.isNotEmpty()){
                // find stack_base
                val symbols = Finders.symbols(parallelLoop.conditions.first())
                val stackBase = symbols.first()
                // 1. set @stack_base annotation
                loop.annotations.add(StackBase(stackBase))
                // 2. replace all stack_base symbols with global_stack_base
                // 3. set global_stack_base to local_stack_base before
                println("Added Stack Base To Parallel For Loop")
            }
        }
    }
}