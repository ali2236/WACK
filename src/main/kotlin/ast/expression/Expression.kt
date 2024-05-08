package ast.expression

import ast.statement.Statement

abstract class Expression : Statement {
    override fun toString(): String {
        val b = StringBuffer()
        c(b)
        return b.toString()
    }

    open fun symbols(): List<Symbol> {
        return listOf()
    }
}















