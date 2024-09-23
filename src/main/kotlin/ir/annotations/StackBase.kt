package ir.annotations

import generation.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor
import ir.statement.SymbolLoad

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

    override fun visit(v: Visitor) {

    }
}