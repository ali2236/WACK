package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class TransferIn(val symbol: Symbol, val index: Int) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@transfer_in (")
        wat.endLine()
        wat.indent++
        symbol.wat(wat)
        wat.indent--
        wat.startLine()
        wat.write(") $index)")
    }

    override fun c(writer: CWriter) {
        writer.write("transfer_in(")
        symbol.c(writer)
        writer.write(", $index)")
    }

    override fun visit(v: Visitor) {

    }
}