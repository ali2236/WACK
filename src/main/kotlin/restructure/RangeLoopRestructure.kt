package restructure

import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.*
import java.lang.Exception

class RangeLoopRestructure : Restructure() {

    override fun restructureBlock(block: Block) {
        super.restructureBlock(block)
        if (block is ConditionLoop) {
            try {
                transformIntoRangeLoop(block)
            } catch (e: Exception) {

            }
        }
    }

    private fun transformIntoRangeLoop(loop: ConditionLoop) {
        val condition = loop.condition
        if (condition is BinaryOP && condition.left is SymbolLoad) {
            val left = condition.left as SymbolLoad
            val init = findRangeLoopInitInParent(left)
            val step = findRangeLoopStep(loop, left)
            val rangeLoop = RangeLoop(init, condition, step, loop.instructions)
            replaceCurrentBlock(rangeLoop)
            }
        }

    private fun findRangeLoopInitInParent(symbol: SymbolLoad): AssignmentStore {
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
        throw Error("symbol $symbol init not found!")
    }

    private fun findRangeLoopStep(loop: ConditionLoop, symbol: SymbolLoad): Increment {
        val steps = BreadthFirstExpressionFinder(Increment::class.java).also { it.visit(loop.instructions) { i, stmt -> } }.result()
        return steps.first()
    }

}