package ir.annotations

import generation.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class Parallel : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@parallel)")
    }

    override fun visit(v: Visitor) {

    }
}