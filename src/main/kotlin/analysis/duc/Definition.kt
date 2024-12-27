package analysis.duc

import ir.statement.Statement
import ir.statement.SymbolLoad

class Definition(val symbol: SymbolLoad, val statement: Statement){
    override fun toString(): String {
        return symbol.toString()
    }
}