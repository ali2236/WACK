package ir.statement

import generation.c.CWriter
import generation.wat.WatWriter

class Comment(val text: String) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("/* $text */\n")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("nop ;; $text")
    }

    override fun c(writer: CWriter) {
        writer.write("/* $text */")
    }

}