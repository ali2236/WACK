package ir.statement

import ir.expression.Expression
import ir.expression.Symbol

open class If(val condition: Expression, val trueBody: Statement, val elseBody: Statement? = null) : Statement {
    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        trueBody.c(out)
        if(elseBody != null){
            out.append("else ")
            elseBody.c(out)
        }
    }

    override fun symbols(): List<Symbol> {
        return condition.symbols() + trueBody.symbols() + elseBody?.symbols().orEmpty()
    }

}

