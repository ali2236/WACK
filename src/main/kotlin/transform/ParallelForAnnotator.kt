package transform

import analysis.ddt.DependenceTester
import compiler.WAPC
import ir.annotations.*
import ir.expression.Load
import ir.finder.BrDepthCheck
import ir.finder.Finders
import ir.statement.Function
import ir.statement.Program

class ParallelForAnnotator : Transformer {
    override fun apply(program: Program) {
        program.allNonSkipFunctions()
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        val loops = DependenceTester(function).testLoops()
        for (parallelLoop in loops) {
            val loop = parallelLoop.loop

            if (BrDepthCheck().also { it.visit(loop){} }.outOfBoundaryJump){
                // dont parallelize when there is jumps(br with depth outside of topLevelRangeLoop)
                continue
            }


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
            /*if(parallelLoop.conditions.isNotEmpty()){
                // find stack_base
                val symbols = Finders.symbols(parallelLoop.conditions.first())
                val stackBase = symbols.first()
                // set @stack_base annotation
                loop.annotations.add(TransferIn(stackBase))
            } else */
            /*if(parallelLoop.loop.symbol is Load) {
                val loopSymbol = parallelLoop.loop.symbol as Load
                // find stack_base
                val symbols = Finders.symbols(loopSymbol)
                val stackBase = symbols.firstOrNull()
                // set @stack_base annotation
                stackBase?.let {
                    loop.annotations.add(TransferIn(stackBase))
                }
            }*/
        }
    }
}