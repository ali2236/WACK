package ir.statement

import generation.wat.WatWriter

class Unreachable : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("trap();\n")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("unreachable")
    }
}