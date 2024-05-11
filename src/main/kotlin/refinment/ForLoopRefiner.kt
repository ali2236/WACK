package refinment

import ir.expression.*
import ir.statement.*
import wasm.WasmValueType
import java.lang.Exception

class ForLoopRefiner : Refiner() {

    override fun refineBlock(block: Block) {
        super.refineBlock(block)
        if (block is Loop && block.condition !is Value && block.condition.symbols().isNotEmpty()) {
            try {
                transformIntoForLoop(block)
            } catch (e : Exception){

            }
        }
    }

    private fun transformIntoForLoop(loop: Loop) {
        val condition = loop.condition
        val symbol = condition.symbols().first()
        val init = findForLoopInitInParent(symbol)
        val step = findForLoopStep(loop, symbol)
        val forLoop = ForLoop(init, condition, step, loop.instructions)
        replaceCurrentBlock(forLoop)
    }

    private fun findForLoopInitInParent(symbol: Symbol): Statement {
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

    private fun findForLoopStep(loop: Loop, symbol: Symbol): Statement {
        val (i, step) = loop.instructions.withIndex()
            .filter { it.value is Increment }
            .last() as IndexedValue<Increment>

        loop.instructions.removeAt(i)

        step.inline = true
        return step
    }

}