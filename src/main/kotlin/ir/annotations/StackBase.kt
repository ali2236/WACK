package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class StackBase(val symbol: Symbol) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@stack (")
        wat.endLine()
        wat.indent++
        symbol.wat(wat)
        wat.indent--
        wat.startLine()
        wat.write("))")
    }

    override fun c(writer: CWriter) {
        writer.write("stack_base(")
        symbol.c(writer)
        writer.write(")")
    }

    override fun visit(v: Visitor) {

    }
}