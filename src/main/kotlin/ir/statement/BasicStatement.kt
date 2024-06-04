package ir.statement

import ir.finder.Visitor

abstract class BasicStatement : Statement {
    override var id: Long? = Statement.newId()

    override fun isExpression(): Boolean = false
    override fun visit(v: Visitor) {

    }
}