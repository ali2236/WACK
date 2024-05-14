package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol


open class Assignment(val symbol: Symbol, var value: Expression, var inline: Boolean = false) : Statement {
    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        value.write(out)
        if(!inline){
            out.append(";\n")
        }
    }

    override fun symbols(): List<Symbol> {
        return symbol.symbols() + value.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(value){value = it}
        )
    }

    override fun toString(): String {
        val buffer = StringBuffer()
        this.write(buffer)
        return buffer.toString()
    }
}