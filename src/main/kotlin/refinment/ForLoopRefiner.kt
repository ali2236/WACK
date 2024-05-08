package refinment

import ast.expression.*

class ForLoopRefiner : Refiner() {

    override fun refineBlock(block: Block) {
        super.refineBlock(block)
        if (block is Loop && block.condition !is Value && block.condition.symbols().isNotEmpty()) {
            transformIntoForLoop(block)
        }
    }

    private fun transformIntoForLoop(loop: Loop) {
        val condition = loop.condition
        val symbol = condition.symbols().first()
        val init = findForLoopInitInParent(symbol)
        val step = findForLoopStep(loop, symbol)
        val forLoop = ForLoop(init, condition, step)
        forLoop.instructions.addAll(loop.instructions)
        replaceCurrentBlock(forLoop)
    }

    private fun findForLoopInitInParent(symbol: Symbol): Expression {
        val parent = parentBlock
        for (i in currentBlockIndex!! downTo 0) {
            val stmt = parent.instructions[i]
            if (stmt is Assignment && stmt.symbol == symbol) {
                // TODO: Check Forward Dependencies (if you use ssa you dont need this)
                parent.instructions.removeAt(i)
                currentBlockIndex = currentBlockIndex!! - 1
                stmt.inline = true
                return stmt
            }
        }
        return Assignment(symbol, Value("??"), inline = true)
    }

    private fun findForLoopStep(loop: Loop, symbol: Symbol): Expression {
        val (i, step) = loop.instructions.withIndex()
            .filter { it.value is Increment }
            .last() as IndexedValue<Increment>

        loop.instructions.removeAt(i)

        step.inline = true
        return step
    }

}