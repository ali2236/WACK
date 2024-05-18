package ir.statement

import generation.WatWriter

class Nop : BasicStatement() {
    override fun write(out: Appendable) {}
    override fun wat(wat: WatWriter) {
        wat.writeLine("nop")
    }
}