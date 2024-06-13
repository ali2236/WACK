package ir.statement

import generation.WatWriter
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor

// symbol++
class Increment(var symbol: Symbol) : BasicStatement() {

    override fun write(out: Appendable) {
        symbol.write(out)
        out.append("++")
        out.append(";\n")
    }

    override fun visit(v: Visitor) {
        v.visit(symbol){symbol = it as Symbol}
    }

    override fun wat(wat: WatWriter) {
        symbol.wat(wat)
        wat.writeLine("${symbol.type}.const 1")
        wat.writeLine("${symbol.type}.add")
        wat.writeLine("${symbol.scope}.set ${symbol.index} ;; $this")
    }
}

