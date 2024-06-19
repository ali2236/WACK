package ir.annotations

import generation.WatWriter
import ir.finder.Visitor

class For : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@for)")
    }

    override fun visit(v: Visitor) {

    }
}