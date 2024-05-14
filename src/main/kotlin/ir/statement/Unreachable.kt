package ir.statement

import ir.expression.Symbol

class Unreachable : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("trap();\n")
    }
}