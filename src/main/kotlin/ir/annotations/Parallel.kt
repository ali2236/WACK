package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor

class Parallel : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@parallel)")
    }

    override fun c(writer: CWriter) {
        writer.write("parallel")
    }

    override fun visit(v: Visitor) {

    }

    override fun toString(): String {
        return "@parallel"
    }
}