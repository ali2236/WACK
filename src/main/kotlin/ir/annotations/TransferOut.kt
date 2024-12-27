package ir.annotations

import generation.wat.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor

class TransferOut(val symbol: Symbol, val index: Int) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@transfer_out")
        symbol.wat(wat)
        wat.write(")")
    }

    override fun visit(v: Visitor) {

    }
}