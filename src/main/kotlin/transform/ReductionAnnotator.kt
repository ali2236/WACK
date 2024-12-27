package transform

import analysis.dfa.Dfa
import compiler.WAPC
import ir.annotations.For
import ir.annotations.Parallel
import ir.finder.ReductionFinder
import ir.statement.Program

class ReductionAnnotator : Transformer {
    override fun apply(program: Program) {
        program.allNonSkipFunctions().forEach { function ->
            val finder = Dfa.from(function).finder()
            val rangeLoops =  function.allNonSkipRangeLoops().parallelFor()
            for (loop in rangeLoops){
                // check for reduction
                val reductions = ReductionFinder(finder, loop).apply { visit(loop){} }.result()
                // mark reduction if found
                if (reductions.isNotEmpty()){
                    if (WAPC.params.reductionParallelization){
                        loop.annotations.addAll(reductions)
                    } else {
                        loop.annotations.removeIf { it is Parallel || it is For }
                    }
                }
            }
        }
    }
}