package optimization

import analysis.ddt.DdgBuilder
import ir.finder.LoopFinder
import ir.statement.Comment
import ir.statement.Function
import ir.statement.Program

class KernelExtraction : Optimizer {

    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach(::applyToFunction)
    }

    private fun applyToFunction(function: Function){
        val ddt = DdgBuilder(function).build()

        val loops = LoopFinder(true).apply { function.visit(this) }.result()
        for ((loop, replace) in loops){
            replace(Comment("a loop was here"))
        }

    }
}