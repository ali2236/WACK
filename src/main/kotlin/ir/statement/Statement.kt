package ir.statement

import ir.expression.Symbol

interface Statement {
    fun c(out: Appendable)

    fun symbols(): List<Symbol>
}