package transform

import ir.annotations.Private
import ir.annotations.Skip
import ir.expression.Expression
import ir.expression.Load
import ir.expression.Symbol
import ir.finder.ExpressionFinder
import ir.finder.SymbolReplacer
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop
import ir.statement.SymbolLoad
import ir.wasm.Index
import ir.wasm.WasmScope

class LocalizeRangeLoopCounter : Transformer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                val paramCount = function.functionData.type.params.size
                val rangeLoops = ExpressionFinder(RangeLoop::class.java)
                    .also { function.visit(it) }
                    .result()
                    .filter { it.hasAnnotation(Private::class.java) }
                // if variable is stack symbol, allocate local symbol

                for (rangeLoop in rangeLoops) {
                    val privateSymbol = rangeLoop.annotations.filterIsInstance<Private>()
                    for (private in privateSymbol){
                        val oldSymbol = private.symbol as Expression
                        if (oldSymbol is Load) {
                            val newSymbol = Symbol(
                                WasmScope.local,
                                oldSymbol.exprType(),
                                Index.number(paramCount + function.functionData.locals.size),
                            )
                            function.functionData.locals.add(newSymbol.type)
                            private.private = newSymbol
                        }
                    }

                    // replace old symbols with new ones
                    val replace = privateSymbol
                        .filter { it.symbol is Load && it.private is Symbol }
                        .associate { Pair(it.symbol as SymbolLoad, it.private as Symbol) }

                    SymbolReplacer(replace).also { function.visit(it) }

                    // TODO: commit back to memory after loop end somehow from last thread?
                }

            }
    }
}