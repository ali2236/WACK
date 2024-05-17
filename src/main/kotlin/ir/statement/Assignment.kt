package ir.statement

import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor


open class Assignment(var symbol: Symbol, var value: Expression, var inline: Boolean = false) : Statement {
    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        value.write(out)
        if(!inline){
            out.append(";\n")
        }
    }

    override fun visit(v: Visitor) {
        v.visit(symbol){symbol = it as Symbol}
        v.visit(value){value = it as Expression}
    }

    override fun toString(): String {
        val buffer = StringBuffer()
        this.write(buffer)
        return buffer.toString()
    }
}