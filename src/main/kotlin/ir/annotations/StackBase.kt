package ir.annotations

import generation.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor
import ir.statement.SymbolLoad

class StackBase(val symbol: Symbol) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@stack (")
        wat.endLine()
        symbol.wat(wat)
        wat.write("))")
        wat.endLine()
    }

    override fun visit(v: Visitor) {

    }
}