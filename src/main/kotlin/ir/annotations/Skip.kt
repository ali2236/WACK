package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor

class Skip : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@skip)")
    }

    override fun c(writer: CWriter) {
        writer.write("skip")
    }

    override fun visit(v: Visitor) {

    }

    override fun toString(): String {
        return "@skip"
    }
}