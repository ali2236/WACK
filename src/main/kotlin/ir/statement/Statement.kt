package ir.statement

import ir.ChildExpression
import ir.expression.Symbol
import ir.finder.Visitable

interface Statement : Visitable {
    fun write(out: Appendable)

    fun expressions(): List<ChildExpression>

}