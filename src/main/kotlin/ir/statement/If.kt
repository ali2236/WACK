package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol

open class If(
    var condition: Expression,
    trueBody: MutableList<Statement> = mutableListOf(),
    var elseBody: Statement? = null,
    brackets: Boolean = true
) : Block(trueBody, brackets = brackets) {
    open val trueBody: Statement
        get() = this as Block

    override fun writeHeader(out: Appendable) {
        out.append("if(")
        condition.write(out)
        out.append(")")
    }
    override fun write(out: Appendable) {
        super.write(out)
        if (elseBody != null) {
            out.append("else ")
            elseBody!!.write(out)
        }
    }

    override fun symbols(): List<Symbol> {
        return condition.symbols() + super.symbols() + elseBody?.symbols().orEmpty()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition) { condition = it }
        )
    }
}

