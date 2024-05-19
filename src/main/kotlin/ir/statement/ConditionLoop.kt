package ir.statement

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.finder.Visitor

open class ConditionLoop(var condition: Statement, instructions: MutableList<Statement>) : Loop(instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("condition-loop")
    }
}