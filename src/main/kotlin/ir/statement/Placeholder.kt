package ir.statement

import ir.expression.Symbol

class Placeholder(val msg: String) : BasicStatement() {
    override fun c(out: Appendable) {
        Comment(msg).c(out)
    }

}