package ir.statement

import generation.c.CWriter
import generation.wat.WatWriter

class Empty : BasicStatement() {
    override fun write(out: Appendable) {

    }

    override fun wat(wat: WatWriter) {

    }

    override fun c(writer: CWriter) {

    }
}