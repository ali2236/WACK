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

    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        super.c(out)
        if (elseBody != null) {
            out.append("else ")
            elseBody!!.c(out)
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

