package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

class Comment(val text: String) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("/* $text */\n")
    }

}