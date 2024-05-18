package ir.statement

import generation.WatWriter

class Return : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("return;")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("return")
    }
}