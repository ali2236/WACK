package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol

open class If(var condition: Expression, var elseBody: Statement? = null) : Block() {
    val trueBody: Statement
        get() = this

    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        super.c(out)
        if(elseBody != null){
            out.append("else ")
            elseBody!!.c(out)
        }
    }

    override fun symbols(): List<Symbol> {
        return condition.symbols() + super.symbols() + elseBody?.symbols().orEmpty()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition){condition = it}
        )
    }
}

