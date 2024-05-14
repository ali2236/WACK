package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol

class RangeLoop(val init: Statement, condition: Expression, val step: Statement, instructions: MutableList<Statement>) : ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("for(")
        init.write(out)
        out.append(';')
        condition.write(out)
        out.append(';')
        step.write(out)
        out.append(')')
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