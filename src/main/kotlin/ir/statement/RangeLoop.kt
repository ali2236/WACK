package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

@Deprecated("Implementation not complete")
class RangeLoop(var init: Statement, condition: Expression, var step: Statement, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("range-loop(")
        init.write(out)
        out.append(';')
        condition.write(out)
        out.append(';')
        step.write(out)
        out.append(')')
    }

    override fun visit(v: Visitor) {
        v.visit(init) { this.init = it }
        v.visit(step) { this.step = it }
        super.visit(v)
    }
}