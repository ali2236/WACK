package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

class Br(val depth: Int) : BasicStatement() {
    override fun c(out: Appendable) {
        Comment("Br $depth").c(out)
    }
}