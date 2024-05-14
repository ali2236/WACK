package ir.expression

import ir.ChildExpression
import ir.statement.Statement

abstract class Expression : Statement {
    override fun toString(): String {
        val b = StringBuffer()
        write(b)
        return b.toString()
    }

    override fun symbols(): List<Symbol> {
        return listOf()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }
}















