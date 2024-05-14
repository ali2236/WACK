package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol

open class ConditionLoop(var condition: Expression, instructions: MutableList<Statement>) : Loop(instructions) {

    override fun symbols(): List<Symbol> {
        return condition.symbols() + super.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition){condition = it}
        )
    }

    override fun writeHeader(out: Appendable) {
        out.append("while(")
        condition.write(out)
        out.append(")")
    }
}