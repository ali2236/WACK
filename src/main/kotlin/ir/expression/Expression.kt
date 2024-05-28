package ir.expression

import ir.statement.Statement
import ir.finder.Visitor

abstract class Expression : Statement {
    override fun toString(): String {
        val b = StringBuffer()
        write(b)
        return b.toString()
    }

    override fun visit(v: Visitor) {

    }

    abstract fun clone(): Expression
}















