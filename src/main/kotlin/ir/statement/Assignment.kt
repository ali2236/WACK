package ir.statement

import ir.expression.Expression
import ir.expression.Symbol


open class Assignment(val symbol: Symbol, val value: Expression, var inline: Boolean = false) : Statement {
    override fun c(out: Appendable) {
        symbol.c(out)
        out.append(" = ")
        value.c(out)
        if(!inline){
            out.append(";\n")
        }
    }

    override fun symbols(): List<Symbol> {
        return symbol.symbols() + value.symbols()
    }
}