package ir.annotations

import generation.WatWriter
import ir.finder.Visitor
import ir.statement.SymbolLoad

class Private(val symbol: SymbolLoad, var private: SymbolLoad? = null) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@private (")
        wat.endLine()
        wat.watDebug = false
        symbol.wat(wat)
        wat.write(")(")
        wat.endLine()
        private?.wat(wat)
        wat.watDebug = true
        wat.write("))")
    }

    override fun visit(v: Visitor) {

    }
}