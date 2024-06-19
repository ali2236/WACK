package ir.annotations

import generation.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class Parallel(var threadId: Symbol? = null) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@parallel $threadId)")
    }

    override fun visit(v: Visitor) {
        threadId?.let {
            v.visit(it) {rep -> this.threadId = rep as Symbol}
        }
    }
}