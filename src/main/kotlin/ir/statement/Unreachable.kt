package ir.statement

import ir.expression.Symbol

class Unreachable : BasicStatement() {
    override fun c(out: Appendable) {
        // out.append("UNREACHABLE;\n")
    }
}