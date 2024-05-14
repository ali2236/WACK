package restructure

import ir.expression.*
import ir.statement.*
import wasm.WasmValueType
import java.lang.Exception

class RangeLoopRestructure : Restructure() {

    override fun restructureBlock(block: Block) {
        super.restructureBlock(block)
        if (block is ConditionLoop && block.condition !is Value && block.condition.symbols().isNotEmpty()) {
            try {
                transformIntoRangeLoop(block)
            } catch (e : Exception){

            }
        }
    }

    private fun transformIntoRangeLoop(loop: ConditionLoop) {
        val condition = loop.condition
        val symbol = condition.symbols().first()
        val init = findRangeLoopInitInParent(symbol)
        val step = findRangeLoopStep(loop, symbol)
        val rangeLoop = RangeLoop(init, condition, step, loop.instructions)
        replaceCurrentBlock(rangeLoop)
    }

    private fun findRangeLoopInitInParent(symbol: Symbol): Statement {
        var current = currentBlock
        var parent = current.parent
        var blockIndex = current.indexInParent
        while (parent != null) {
            for (i in (blockIndex!! - 1) downTo 0) {
                val stmt = parent.instructions[i]
                if (stmt is Assignment && stmt.symbol == symbol) {
                    // TODO: Check Forward Dependencies (if you use ssa you dont need this)
                    parent.instructions.removeAt(i)
                    current.indexInParent = blockIndex - 1
                    stmt.inline = true
                    return stmt
                }
            }
            current = parent
            parent = current.parent
            blockIndex = current.indexInParent
        }
        return Assignment(symbol, Value(WasmValueType.Unknown,"??"), inline = true)
    }

    private fun findRangeLoopStep(loop: ConditionLoop, symbol: Symbol): Statement {
        val (i, step) = loop.instructions.withIndex()
            .filter { it.value is Increment }
            .last() as IndexedValue<Increment>

        loop.instructions.removeAt(i)

        step.inline = true
        return step
    }

}