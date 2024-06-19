package ir.annotations

import generation.WatWriter
import ir.finder.Visitor

class Skip : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@skip)")
    }

    override fun visit(v: Visitor) {

    }
}