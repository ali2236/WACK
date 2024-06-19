package ir.annotations

import generation.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class ThreadId(var symbol: Symbol) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("@threadId $symbol")
    }

    override fun visit(v: Visitor) {
        v.visit(symbol){this.symbol = it as Symbol}
    }
}