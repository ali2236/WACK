package refinment

import ir.expression.BinaryOP
import ir.expression.Increment
import ir.expression.Operator
import ir.expression.Value
import ir.statement.Assignment
import ir.statement.Block
import ir.statement.ForLoop
import java.lang.Exception

class ForLoopRangeRefinement : Refiner() {

    override fun refineBlock(block: Block) {
        super.refineBlock(block)
        if (block is ForLoop) {
            refineForLoop(block)
        }
    }

    private fun refineForLoop(loop: ForLoop) {
        // pattern: <symbol>=<start>;<symbol><cond><end>;<step>

        // is it already a range loop
        if (loop.condition is BinaryOP) {
            when (loop.condition.operator) {
                Operator.le, Operator.lt, Operator.gt, Operator.ge -> throw NotRangeLoopException("Already a range Loop")
            }
            if (!listOf(Operator.neq).contains(loop.condition.operator)) {
                throw NotRangeLoopException("operator not supported")
            }
        } else {
            throw NotRangeLoopException("binary operator not found in loop condition")
        }

        // loop direction
        val direction: LoopStepDirection
        if (loop.step is Increment && loop.step.value is BinaryOP) {
            direction = when (loop.step.value.operator) {
                Operator.add -> LoopStepDirection.Up
                Operator.sub -> LoopStepDirection.Down
                else -> throw NotRangeLoopException("No Direction!")
            }
        } else {
            throw NotRangeLoopException("No Direction!(2)")
        }

        // where does the loop start
        var from: Int = 0
        if (loop.init is Assignment && loop.init.value is Value) {
            from = loop.init.value.value.toInt()
        } else {
            throw NotRangeLoopException("No Const Init")
        }

        // where does the loop end
        var end: Int = 0
        if (loop.condition is BinaryOP && loop.condition.right is Value) {
            if (loop.condition.operator == Operator.neq) {
                end = loop.condition.right.value.toInt()
            } else {
                throw NotRangeLoopException("operator not supported(2)")
            }
        } else {
            throw NotRangeLoopException("No Const goal")
        }

        // change condition
        val op = loop.condition.operator
        loop.condition.operator = if (op == Operator.neq) {
            when (direction) {
                LoopStepDirection.Up -> Operator.lt
                LoopStepDirection.Down -> Operator.gt
            }
        } else {
            loop.condition.operator
        }

    }

    private enum class LoopStepDirection {
        Up, Down
    }

    private class NotRangeLoopException(message: String?) : Exception(message)
}