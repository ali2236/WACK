package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

class RangeLoop(var init: Statement, condition: Statement, var step: Statement, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("range-loop")
    }
}