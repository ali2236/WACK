package ir.annotations

import generation.WatWriter
import ir.expression.Symbol

class ThreadId(val symbol: Symbol) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("@threadId $symbol")
    }
}