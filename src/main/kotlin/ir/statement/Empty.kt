package ir.statement

import generation.WatWriter

class Empty : BasicStatement() {
    override fun write(out: Appendable) {

    }

    override fun wat(wat: WatWriter) {

    }
}