package analysis.duc

import ir.statement.Statement
import ir.statement.SymbolLoad

class SymbolUseDefChain(
    val symbol: SymbolLoad,
    val definitions: MutableList<Definition> = mutableListOf(),
    val uses: MutableList<Use> = mutableListOf(),
) {
    fun addDef(statement: Statement) {
        if (definitions.any { it.statement == statement }){
            return
        }
        val def = Definition(symbol, statement)
        definitions.add(def)
    }

    fun addUse(statement: Statement) {
        if (uses.any { it.statement == statement }){
            return
        }
        val use = Use(definitions.lastOrNull(), statement)
        uses.add(use)
    }

    override fun toString(): String {
        return symbol.toString()
    }
}