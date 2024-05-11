package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

class Comment(val text: String) : BasicStatement() {
    override fun c(out: Appendable) {
        out.append("/* $text */\n")
    }

}