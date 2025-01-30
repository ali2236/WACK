package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class ThreadId(var symbol: Symbol) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("@threadId $symbol")
    }

    override fun c(writer: CWriter) {
        writer.write("thread_id(")
        symbol.c(writer)
        writer.write(")")
    }

    override fun visit(v: Visitor) {
        v.visit(symbol){this.symbol = it as Symbol}
    }

    override fun toString(): String {
        return "@thread_id($symbol)"
    }
}