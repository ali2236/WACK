package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor

class For : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@for)")
    }

    override fun c(writer: CWriter) {
        writer.write("for")
    }

    override fun visit(v: Visitor) {

    }

    override fun toString(): String {
        return "@for"
    }
}