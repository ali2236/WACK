package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor


open class Assignment(
    var symbol: Symbol,
    var value: Expression,
    var inline: Boolean = false,
    var tee: Boolean = false
) : Statement, Assignee {


    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        value.write(out)
        if (!inline) {
            out.append(";\n")
        }
        // TODO: Tee
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        if (tee) {
            wat.writeLine("${symbol.scope}.tee ${symbol.index}")
        } else {
            wat.writeLine("${symbol.scope}.set ${symbol.index}")

        }
    }

    override fun visit(v: Visitor) {
        v.visit(symbol) { symbol = it as Symbol }
        v.visit(value) { value = it as Expression }
    }

    override fun assignedWith(): Expression {
        return value
    }

    override fun assignedTo(): Assignable {
        return symbol
    }

    override fun replaceAssign(newValue: Expression) {
        value = newValue
    }

    override fun toString(): String {
        val buffer = StringBuffer()
        this.write(buffer)
        return buffer.toString()
    }

}