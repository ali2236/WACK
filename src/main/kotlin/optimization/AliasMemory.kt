package optimization

import analysis.dfa.Dfa
import analysis.dfa.DfaValue
import ir.expression.Load
import ir.expression.Symbol
import ir.finder.ReplaceableFinder
import ir.statement.Assignment
import ir.statement.Function
import ir.statement.Program
import ir.statement.Store
import wasm.Index
import wasm.WasmScope

class AliasMemory : Optimizer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { it.instructions.isNotEmpty() }
            .forEach { function ->

                // Alias Map
                val aliases = mutableMapOf<Load, Symbol>()
                val makeAlias: (Load) -> Unit = { load ->
                    aliases[load] = Symbol(WasmScope.local, load.type, Index.next(function.functionData.locals))
                    function.functionData.locals.add(load.type)
                }

                // Alias Map Initialize
                val dfa = Dfa.from(function)
                val exitNode = dfa.nodes[1]
                exitNode.OUT.facts
                    .filter { fact -> fact.symbol is Load }
                    .forEach { fact -> makeAlias(fact.symbol as Load) }

                // Find All loads
                val rLoads = ReplaceableFinder(Load::class.java).also { it.visit(function) {} }.result()

                // find All Stores
                val rStores = ReplaceableFinder(Store::class.java).also { it.visit(function) {} }.result()

                // Replace loads with Alias
                rLoads
                    .filter { aliases.containsKey(it.statement) }
                    .forEach { (load, replace) ->
                        replace(aliases[load]!!)
                    }

                aliases.entries.forEach {(key, value) ->
                    println("function ${function.functionData.index}: $key -> $value")
                }

                // Replace stores with assignment
                rStores
                    .filter { aliases.containsKey(it.statement.symbol) }
                    .forEach { (store, replace) ->
                        val symbol = aliases[store.symbol]!!
                        replace(Assignment(symbol, store.data))
                    }
            }
    }
}