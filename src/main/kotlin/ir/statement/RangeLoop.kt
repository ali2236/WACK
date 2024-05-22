package ir.statement

import generation.WatWriter
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Increment
import ir.expression.Value
import ir.finder.Visitor

class RangeLoop(var init: Assignee, condition: BinaryOP, var step: Increment, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        val to =
        out.append("range-loop(${from()} to ${to()})")
    }

    fun from(): Expression {
        return init.assignedWith()
    }

    fun to() : Expression {
        return when (condition) {
            is BinaryOP -> {
                (condition as BinaryOP).right
            }

            else -> throw Error()
        }
    }
}