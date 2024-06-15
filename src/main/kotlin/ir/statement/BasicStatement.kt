package ir.statement

import ir.finder.Visitor

abstract class BasicStatement : Statement {
    override var id: Long? = Statement.newId()
    override fun visit(v: Visitor) {

    }

    override fun toString(): String {
        val b = StringBuffer()
        write(b)
        return b.toString()
    }
}