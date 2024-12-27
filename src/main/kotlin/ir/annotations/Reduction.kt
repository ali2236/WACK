package ir.annotations

import generation.wat.WatWriter
import ir.expression.BinaryOP
import ir.finder.Visitor
import ir.statement.SymbolLoad

class Reduction(val symbol: SymbolLoad, val operator: BinaryOP.Operator) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@reduce)")
    }

    override fun visit(v: Visitor) {

    }
}