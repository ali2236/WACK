package ir.statement

import ir.ChildExpression
import ir.expression.Symbol
import ir.finder.Visitor

abstract class BasicStatement : Statement {

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }

    override fun visit(v: Visitor) {

    }
}