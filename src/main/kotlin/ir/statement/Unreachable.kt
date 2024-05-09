package ir.statement

import ir.expression.Symbol

class Unreachable : Statement {
    override fun c(out: Appendable) {
        out.append("UNREACHABLE;\n")
    }

    override fun symbols(): List<Symbol> {
        return listOf()
    }
}