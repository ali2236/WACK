package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor

class RangeLoop(val init: Statement, condition: Expression, val step: Statement, instructions: MutableList<Statement>) : ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("range-loop(")
        init.write(out)
        out.append(';')
        condition.write(out)
        out.append(';')
        step.write(out)
        out.append(')')
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition){condition = it}
        ) + init.expressions() + step.expressions()
    }

    override fun visit(v: Visitor) {
        v.visit(init)
        v.visit(step)
        super.visit(v)
    }
}