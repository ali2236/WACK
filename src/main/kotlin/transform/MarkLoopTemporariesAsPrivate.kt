package transform

import analysis.dfa.Dfa
import compiler.WAPC
import ir.annotations.For
import ir.annotations.Private
import ir.annotations.Skip
import ir.expression.Load
import ir.expression.Symbol
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ExpressionFinder
import ir.finder.StackVariableFinder
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop
import ir.statement.Store

class MarkLoopTemporariesAsPrivate : Transformer {

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                // find all top level for loops
                val topLevelForLoops =
                    BreadthFirstExpressionFinder(RangeLoop::class.java, !WAPC.params.parallelizeInnerLoops)
                        .also { function.visit(it) }
                        .result()
                        .filter { it.hasAnnotation(For::class.java) }

                // find def symbols(that are not counter symbols) and privatize them
                // then propagate to top level loop
                for (forLoop in topLevelForLoops) {

                    // privatize all symbols
                    val vars = ExpressionFinder(Symbol::class.java, setOf(Load::class.java, Store::class.java))
                        .also { forLoop.visit(it) }
                        .result()
                        .toSet()

                    vars.forEach {
                        forLoop.annotations.add(Private(it))
                    }

                    // privatize all non-array stack-variables
                    // avoid:
                    // L1 = L0<<2+368
                    // M[L1+0] = ...
                    val dfa = Dfa.from(function)
                    val loopPrivate = forLoop.annotations.filterIsInstance<Private>().map { it.symbol }.toSet()
                    val stackVars = StackVariableFinder(forLoop, loopPrivate, dfa).result()

                    stackVars.forEach {
                        forLoop.annotations.add(Private(it))
                    }
                }
            }
    }

}