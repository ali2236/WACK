package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

interface Statement {
    fun c(out: Appendable)

    fun symbols(): List<Symbol>

    fun expressions(): List<ChildExpression>
}