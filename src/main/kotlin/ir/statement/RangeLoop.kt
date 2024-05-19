package ir.statement

import generation.WatWriter
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Increment
import ir.finder.Visitor

class RangeLoop(var init: Assignee, condition: BinaryOP, var step: Increment, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        val from = init.assignedWith().toString()
        val to =  when(condition){
            is BinaryOP -> {
                (condition as BinaryOP).right.toString()
            }
            else -> "?"
        }
        out.append("range-loop(${from} to ${to})")
    }
}