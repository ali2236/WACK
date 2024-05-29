package ir.statement

import ir.expression.Expression


// symbol, load
interface SymbolLoad : Statement {
    fun clone() : Expression
}