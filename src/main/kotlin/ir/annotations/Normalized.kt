package ir.annotations

import generation.wat.WatWriter
import ir.finder.Visitor

class Normalized : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@normalized)")
    }

    override fun visit(v: Visitor) { }

    override fun toString(): String {
        return "@normalized"
    }
}