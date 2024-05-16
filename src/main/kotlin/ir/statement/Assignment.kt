package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor


open class Assignment(val symbol: Symbol, var value: Expression, var inline: Boolean = false) : Statement {
    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        value.write(out)
        if(!inline){
            out.append(";\n")
        }
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(value){value = it}
        )
    }

    override fun visit(v: Visitor) {
        v.visit(symbol)
        v.visit(value)
    }

    override fun toString(): String {
        val buffer = StringBuffer()
        this.write(buffer)
        return buffer.toString()
    }
}