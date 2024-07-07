package transform.restructure.archive

import ir.expression.*
import ir.finder.ExpressionFinder
import ir.finder.Replaceable
import ir.finder.ReplaceableFinder
import ir.statement.Function
import ir.statement.SymbolLoad
import ir.wasm.Index
import ir.wasm.WasmScope
import transform.restructure.Restructure

// for each function:
// find all loads
// filter with pattern load.address ~= <symbol> + offset
// find most common <symbol>
// alias loads that use that symbol
@Deprecated("Doesn't Work")
// because:
// 1. multiple byte sizes can be stored in a single address
// 2. doing this globally is not a good idea
class StackSymbolRestructure : Restructure() {

    override fun restructureFunction(function: Function) {
        val replaceableLoads = ReplaceableFinder(Load::class.java).also { it.visit(function) {} }.result()

        val symbolPortioned = mutableMapOf<Symbol, MutableList<Replaceable<Load>>>()
        for (replaceableLoad in replaceableLoads) {
            val symbols = ExpressionFinder(SymbolLoad::class.java)
                .also { it.visit(replaceableLoad.statement.address) {} }
                .result()
            if (symbols.size != 1) continue
            val symbol = symbols.first()
            if (symbol !is Symbol) continue
            val list = symbolPortioned.getOrDefault(symbol, mutableListOf())
            list.add(replaceableLoad)
            symbolPortioned[symbol] = list
        }

        if (symbolPortioned.isEmpty()) return

        val mostMatchedSymbol =
            symbolPortioned.entries
                .map { Pair(it.key, it.value.size) }
                .maxByOrNull { it.second }!!
                .first

        val loadsWithMatchedSymbol = symbolPortioned[mostMatchedSymbol]!!
        val addressSymbol = mutableMapOf<Int, Symbol>() // offset -> symbol
        for ((load, replace) in loadsWithMatchedSymbol) {
            val offset =
                load.offset + if (load.address is BinaryOP) ExpressionFinder(Value::class.java).also { it.visit(load.address) {} }
                    .result().single().value.toInt() else 0

            if(!addressSymbol.containsKey(offset)){
                val type = load.type
                val index = function.functionData.type.params.size + function.functionData.locals.size
                function.functionData.locals.add(type)
                addressSymbol[offset] = Symbol(WasmScope.local, type, Index.number(index))
            }
            val offsetSymbol =  addressSymbol[offset]!!
            replace(offsetSymbol)
        }
    }

}