package transform

import ir.finder.ReductionFinder
import ir.statement.Program

class ReductionAnnotator : Transformer {
    override fun apply(program: Program) {
        program.allNonSkipFunctions()
            .flatMap { it.allNonSkipRangeLoops() }
            .parallelFor()
            .forEach { loop ->
                // check for reduction
                val reductions = ReductionFinder().apply { visit(loop){} }.result()
                // mark reduction if found
                loop.annotations.addAll(reductions)
            }
    }
}