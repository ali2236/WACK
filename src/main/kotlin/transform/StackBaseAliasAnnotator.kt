package transform

import compiler.WAPC
import ir.annotations.Skip
import ir.annotations.StackBase
import ir.expression.Load
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.Finders
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop

class StackBaseAliasAnnotator : Transformer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { applyToFunction(it) }
    }

    private fun applyToFunction(function: Function) {
        // How do I detect stack referencing?
        val rangeLoops =
            BreadthFirstExpressionFinder(RangeLoop::class.java, !WAPC.params!!.parallelizeInnerLoops).also { it.visit(function) {} }.result()
        for (loop in rangeLoops) {
            if (!loop.hasAnnotation(StackBase::class.java)) {
                if (loop.symbol is Load) {
                    Finders.symbols(loop.symbol).firstOrNull()?.let { sb ->
                        loop.annotations.add(StackBase(sb))
                    }
                } else {
                    // Is there no stack variables used?
                }
            }
        }
    }
}