package refinment

import ast.expression.*

class WhileLoopRefiner : Refiner() {

    override fun refineBlock(block: Block) {
        super.refineBlock(block)
        if (block is Loop) {
            refineLoop(block)
        }
    }

    private fun refineLoop(loop: Loop) {
        val conditions = loop.breakConditions()
        val continueConditions = conditions.filter {
            it.value.trueBody is Continue && loop.instructions[it.index + 1] is Break
        }
        if (continueConditions.size == 1) {
            val indexedCondition = continueConditions.last()
            val condition = indexedCondition.value

            // remove original condition from code
            loop.instructions.removeAt(indexedCondition.index + 1)
            loop.instructions.removeAt(indexedCondition.index)

            // update loop condition
            loop.condition = condition.condition
        }
    }


}