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
        if(conditions.isNotEmpty()){
            doWhileConditionStyle(loop, conditions)
        } else {
            //whileConditionStyle(loop)
        }
    }

/*    private fun whileConditionStyle(loop: Loop){
        val f = loop.instructions.first()
        val l = loop.instructions.last()
        if(f is If && f.elseBody == null && f.instructions.last() is Br && l is *//*Break*//* Br){
            // remove break
            loop.instructions.removeLast()

            // move condition to loop
            loop.condition = f.condition

            // move true block to loop instructions
            val instr = (f.trueBody as Block).instructions
            loop.instructions.addAll(instr)

            // remove last instructions (Br)
            loop.instructions.removeLast()

            // move sub-block parent pointers to loop
            loop.instructions.forEachIndexed { i, stmt ->
                if(stmt is Block){
                    stmt.parent = loop
                    stmt.indexInParent = i
                }
            }

            // remove if
            loop.instructions.removeFirst()
        }
    }*/

    private fun doWhileConditionStyle(loop: Loop, conditions: List<IndexedValue<BrIf>>){
        val continueConditions = conditions.filter {
            it.value.trueBody is Br && (it.value.trueBody as Br).depth == 0
        }
        if (continueConditions.size == 1) {
            val indexedCondition = continueConditions.last()
            val condition = indexedCondition.value


            // remove original condition from code
            loop.instructions.removeAt(indexedCondition.index)

            // update loop condition
            val conditionLoop = ConditionLoop(condition.condition, loop.instructions)
            replaceCurrentBlock(conditionLoop)

            // update parent
            conditionLoop.instructions.forEach {
                if(it is Block){
                    it.parent = conditionLoop
                }
            }
        }
    }


}