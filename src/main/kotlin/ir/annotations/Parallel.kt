package ir.annotations

import generation.WatWriter
import ir.expression.Symbol

class Parallel(var threadId: Symbol? = null) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@parallel $threadId)")
    }
}