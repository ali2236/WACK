package ir.statement

import generation.WatWriter

class Comment(val text: String) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("/* $text */\n")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("nop ;; $text")
    }

}