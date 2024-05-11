package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

abstract class BasicStatement : Statement {

    override fun symbols(): List<Symbol> {
        return listOf()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }
}