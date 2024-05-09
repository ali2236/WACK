package ir.expression

import ir.statement.Statement

abstract class Expression : Statement {
    override fun toString(): String {
        val b = StringBuffer()
        c(b)
        return b.toString()
    }

    override fun symbols(): List<Symbol> {
        return listOf()
    }
}















