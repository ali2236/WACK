package ir.finder

import ir.expression.Symbol
import ir.statement.Statement
import ir.statement.SymbolLoad

class SymbolMatcher(val symbol: SymbolLoad) : Visitor() {
    private var matches = 0
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if(v == symbol){
            matches++
        }
        super.visit(v, replace)
    }

    public fun matches() : Int {
        return matches
    }
}