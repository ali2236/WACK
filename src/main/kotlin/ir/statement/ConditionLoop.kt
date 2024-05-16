package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor

open class ConditionLoop(var condition: Expression, instructions: MutableList<Statement>) : Loop(instructions) {

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition){condition = it}
        )
    }

    override fun visit(v: Visitor) {
        v.visit(condition)
        super.visit(v)
    }

    override fun writeHeader(out: Appendable) {
        out.append("condition-loop(")
        condition.write(out)
        out.append(")")
    }
}