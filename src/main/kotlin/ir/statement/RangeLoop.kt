package ir.statement

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Increment

class RangeLoop(var init: AssignmentStore, condition: BinaryOP, var step: Increment, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
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