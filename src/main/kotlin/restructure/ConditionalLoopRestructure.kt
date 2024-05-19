package restructure

import ir.statement.*

class ConditionalLoopRestructure : Restructure() {

    override fun restructureBlock(block: Block) {
        super.restructureBlock(block)
        if (block is Loop) {
            refineLoop(block)
        }
    }

    private fun refineLoop(loop: Loop) {
        val conditions = loop.breakConditions()
        if (conditions.isNotEmpty()) {
            doWhileConditionStyle(loop, conditions)
        } else {
            whileConditionStyle(loop)
        }
    }

    private fun whileConditionStyle(loop: Loop) {
        val f = loop.instructions.first()
        if (f is If && f.elseBody == null && f.instructions.last() is Br && (f.instructions.last() as Br).depth == 1) {
            // make conditional loop
            // move true block to loop instructions
            val conditionLoop = ConditionLoop(f.condition, loop.instructions)

            // change last Br depth to 0
            //(conditionLoop.instructions.last() as Br).depth = 0

            // remove if
            //conditionLoop.instructions.removeFirst()

            // move sub-block parent pointers to loop
            conditionLoop.instructions.forEachIndexed { i, stmt ->
                if (stmt is Block) {
                    stmt.parent = loop
                    stmt.indexInParent = i
                }
            }

            replaceCurrentBlock(conditionLoop)
        }
    }

    private fun doWhileConditionStyle(loop: Loop, conditions: List<IndexedValue<BrIf>>) {
        val continueConditions = conditions.filter {
            it.value.onTrue is Br && (it.value.onTrue as Br).depth == 0
        }
        if (continueConditions.size == 1) {
            val indexedCondition = continueConditions.last()
            val condition = indexedCondition.value


            // remove original condition from code
            //loop.instructions.removeAt(indexedCondition.index)

            // update loop condition
            val conditionLoop = ConditionLoop(condition, loop.instructions)
            replaceCurrentBlock(conditionLoop)

            // update parent
            conditionLoop.instructions.forEach {
                if (it is Block) {
                    it.parent = conditionLoop
                }
            }
        }
    }


}