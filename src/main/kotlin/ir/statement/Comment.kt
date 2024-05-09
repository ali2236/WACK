package ir.statement

import ir.expression.Symbol

class Comment(val text: String) : Statement {
    override fun c(out: Appendable) {
        out.append("/* $text */\n")
    }

    override fun symbols(): List<Symbol> {
        return listOf()
    }
}