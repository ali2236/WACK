package ir.statement

import ir.expression.Expression
import ir.finder.Visitor

open class ConditionLoop(var condition: Expression, instructions: MutableList<Statement>) : Loop(instructions) {

    override fun visit(v: Visitor) {
        v.visit(condition){condition = it as Expression}
        super.visit(v)
    }

    override fun writeHeader(out: Appendable) {
        out.append("condition-loop(")
        condition.write(out)
        out.append(")")
    }
}