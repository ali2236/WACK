package ir.expression

import ir.ChildExpression
import ir.statement.Statement
import ir.finder.Visitor

abstract class Expression : Statement {
    override fun toString(): String {
        val b = StringBuffer()
        write(b)
        return b.toString()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }

    override fun visit(v: Visitor) {

    }
}















