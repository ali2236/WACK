package ir.statement

import generation.c.CWriter
import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

class Drop(var value: Expression) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("drop(")
        value.write(out)
        out.append(");\n")
    }

    override fun visit(v: Visitor) {
        v.visit(value){ this.value = it as Expression}
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        wat.writeLine("drop", this)
    }

    override fun c(writer: CWriter) {
        writer.inLine {
            value.c(writer)
        }
    }
}