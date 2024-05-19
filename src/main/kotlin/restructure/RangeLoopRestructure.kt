package restructure

import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ExpressionFinder
import ir.finder.Finders
import ir.statement.*
import wasm.WasmValueType
import java.lang.Exception

class RangeLoopRestructure : Restructure() {

    override fun restructureBlock(block: Block) {
        super.restructureBlock(block)
        if (block is ConditionLoop && block.condition !is Value && Finders.symbols(block.condition).isNotEmpty()) {
            try {
                transformIntoRangeLoop(block)
            } catch (e: Exception) {

            }
        }
    }

    private fun transformIntoRangeLoop(loop: ConditionLoop) {
        val condition = loop.condition
        if (condition is BinaryOP && condition.left is Assignable) {
            val left = condition.left as Assignable
            val init = findRangeLoopInitInParent(left)
            val step = findRangeLoopStep(loop, left)
            val rangeLoop = RangeLoop(init, condition, step, loop.instructions)
            replaceCurrentBlock(rangeLoop)
            }
        }

    private fun findRangeLoopInitInParent(symbol: Assignable): Assignee {
        var current = currentBlock
        var parent = current.parent
        var blockIndex = current.indexInParent
        while (parent != null) {
            for (i in (blockIndex!! - 1) downTo 0) {
                val stmt = parent.instructions[i]
                when(symbol) {
                    is Symbol -> {
                        if (stmt is Assignment && stmt.symbol == symbol) {
                            // TODO: Check Forward Dependencies (if you use ssa you dont need this)
                            // parent.instructions.removeAt(i)
                            // current.indexInParent = blockIndex - 1
                            // stmt.inline = true
                            return stmt
                        }
                    }
                    is Load -> {
                        if (stmt is Store && stmt.symbol == symbol) {
                            // TODO: Check Forward Dependencies (if you use ssa you dont need this)
                            // parent.instructions.removeAt(i)
                            // current.indexInParent = blockIndex - 1
                            // stmt.inline = true
                            return stmt
                        }
                    }
                }
            }
            current = parent
            parent = current.parent
            blockIndex = current.indexInParent
        }
        throw Error()
    }

    private fun findRangeLoopStep(loop: ConditionLoop, symbol: Assignable): Increment {
        val steps = BreadthFirstExpressionFinder(Increment::class.java).also { it.visit(loop.instructions) { i, stmt -> } }.result()
        return steps.first()
    }

}