package transform

import compiler.WAPC
import ir.annotations.Parallel
import ir.annotations.Skip
import ir.annotations.TransferIn
import ir.expression.Load
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.Finders
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop

class LocalsTransferInAnnotator : Transformer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        val parallelLoops =
            BreadthFirstExpressionFinder(RangeLoop::class.java, !WAPC.params.parallelizeInnerLoops).also {
                function.visit(it)
            }.result().filter { it.hasAnnotation(Parallel::class.java) }
        for (loop in parallelLoops) {
            val symbolsUsed = Finders.symbols(loop).toSet()
            val annotations = symbolsUsed.mapIndexed { i, s -> TransferIn(s, i) }
            loop.annotations.addAll(annotations)
        }
    }
}