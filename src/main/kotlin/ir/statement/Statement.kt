package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

interface Statement {
    fun write(out: Appendable)

    fun symbols(): List<Symbol>

    fun expressions(): List<ChildExpression>
}