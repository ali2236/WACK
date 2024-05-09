package ir.statement

import ir.expression.Expression
import ir.expression.Symbol

class Continue : Statement {
    override fun c(out: Appendable) {
        out.append("continue;\n")
    }

    override fun symbols(): List<Symbol> {
        return listOf()
    }
}