package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

class Drop(var value: Expression) : Statement {
    override fun write(out: Appendable) {
        value.write(out)
        out.append(";\n")
    }

    override fun visit(v: Visitor) {
        v.visit(value){ this.value = it as Expression}
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        wat.writeLine("drop")
    }
}