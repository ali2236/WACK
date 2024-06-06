package restructure

import ir.expression.BinaryOP
import ir.statement.Increment
import ir.expression.Value
import ir.statement.Assignment
import ir.statement.Block
import ir.statement.RangeLoop
import java.lang.Exception

class RangeLoopRangeRefinement : Restructure() {

    override fun restructureBlock(block: Block) {
        super.restructureBlock(block)
        if (block is RangeLoop) {
            try {
                refineForLoop(block)
            } catch (e: NotRangeLoopException) {

            }
        }
    }

    private fun refineForLoop(loop: RangeLoop) {
        // pattern: <symbol>=<start>;<symbol><cond><end>;<step>

        // is it already a range loop
        if (loop.condition is BinaryOP) {
            val binOp = loop.condition as BinaryOP
            when (binOp.operator) {
                BinaryOP.Operator.le, BinaryOP.Operator.lt, BinaryOP.Operator.gt, BinaryOP.Operator.ge -> throw NotRangeLoopException(
                    "Already a range Loop"
                )
            }
            if (!listOf(BinaryOP.Operator.neq).contains(binOp.operator)) {
                throw NotRangeLoopException("operator not supported")
            }
        } else {
            throw NotRangeLoopException("binary operator not found in loop condition")
        }

        // loop direction
        val direction = LoopStepDirection.Up

        // where does the loop start
        var from: Int = 0
        if (loop.init is Assignment && (loop.init as Assignment).value is Value) {
            val v = (loop.init as Assignment).value as Value
            from = v.value.toInt()
        } else {
            throw NotRangeLoopException("No Const Init")
        }

        // where does the loop end
        var end: Int = 0
        if (loop.condition is BinaryOP) {
            val binOp = loop.condition as BinaryOP
            if (binOp.right is Value) {
                val v = binOp.right as Value
                if (binOp.operator == BinaryOP.Operator.neq) {
                    end = v.value.toInt()
                } else {
                    throw NotRangeLoopException("operator not supported(2)")
                }
            }
        } else {
            throw NotRangeLoopException("No Const goal")
        }

        // change condition
        val op = (loop.condition as BinaryOP).operator
        (loop.condition as BinaryOP).operator = if (op == BinaryOP.Operator.neq) {
            when (direction) {
                LoopStepDirection.Up -> BinaryOP.Operator.lt
                LoopStepDirection.Down -> BinaryOP.Operator.gt
            }
        } else {
            (loop.condition as BinaryOP).operator
        }

    }

    private enum class LoopStepDirection {
        Up, Down
    }

    private class NotRangeLoopException(message: String?) : Exception(message)
}