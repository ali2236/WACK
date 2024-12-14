package transform

import analysis.ddt.DependenceTester
import compiler.WAPC
import ir.annotations.*
import ir.expression.Load
import ir.finder.Finders
import ir.statement.Function
import ir.statement.Program

class ParallelForAnnotator : Transformer {
    override fun apply(program: Program) {
        if(!WAPC.params!!.parallelize){
            return
        }
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        val loops = DependenceTester(function).testLoops()
        for (parallelLoop in loops) {
            val loop = parallelLoop.loop
            loop.annotations.add(Parallel())
            loop.annotations.add(For())
            //println("Loop Marked as Parallel For")
            if(parallelLoop.conditions.isNotEmpty()){
                for (condition in parallelLoop.conditions){
                    loop.annotations.add(OnlyIf(condition))
                }
                //println("Added Conditions To Parallel For Loop")
            }
            // generally alias stack access
            if(parallelLoop.conditions.isNotEmpty()){
                // find stack_base
                val symbols = Finders.symbols(parallelLoop.conditions.first())
                val stackBase = symbols.first()
                // set @stack_base annotation
                loop.annotations.add(StackBase(stackBase))
            } else if(parallelLoop.loop.symbol is Load) {
                val loopSymbol = parallelLoop.loop.symbol as Load
                // find stack_base
                val symbols = Finders.symbols(loopSymbol)
                val stackBase = symbols.firstOrNull()
                // set @stack_base annotation
                stackBase?.let {
                    loop.annotations.add(StackBase(stackBase))
                }
            }
        }
    }
}