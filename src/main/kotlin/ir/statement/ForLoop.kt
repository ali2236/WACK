package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol

class ForLoop(val init: Statement, var condition: Expression, val step: Statement, instructions : MutableList<Statement>) : Block(instructions) {
    override fun c(out: Appendable) {
        out.append("for(")
        init.c(out)
        out.append(';')
        condition.c(out)
        out.append(';')
        step.c(out)
        out.append(')')
        super.c(out)
    }

    override fun symbols(): List<Symbol> {
        return init.symbols() + condition.symbols() + step.symbols() + super.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition){condition = it}
        ) + init.expressions() + step.expressions()
    }
}