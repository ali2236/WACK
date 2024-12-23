package transform.restructure

import ir.annotations.Skip
import ir.finder.LoopFinder
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import transform.Transformer

class ConditionalLoopRestructure : Transformer {

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                val loops = LoopFinder(Loop::class.java, topLevelOnly = false).also { function.visit(it) }.result()
                loops.forEach { refineLoop(it.statement, it.replace) }
            }
    }

    private fun refineLoop(loop: Loop, replace: (Statement) -> Unit) {
        val conditions = loop.breakConditions()
        if (conditions.isNotEmpty()) {
            doWhileConditionStyle(loop, conditions, replace)
        } else {
            whileConditionStyle(loop, replace)
        }
    }

    private fun whileConditionStyle(loop: Loop,  replace: (Statement) -> Unit) {
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

            conditionLoop.parent = loop.parent
            conditionLoop.indexInParent = loop.indexInParent
            replace(conditionLoop)
            conditionLoop.instructions.forEachIndexed { i, stmt ->
                if (stmt is Block){
                    stmt.parent = conditionLoop
                    stmt.indexInParent = i
                }
            }
        }
    }

    private fun doWhileConditionStyle(loop: Loop, conditions: List<IndexedValue<BrIf>>, replace: (Statement) -> Unit) {
        val continueConditions = conditions.filter {
            it.value.onTrue is Br && (it.value.onTrue as Br).depth == 0
        }
        if (continueConditions.size == 1) {
            val indexedCondition = continueConditions.last()
            val condition = indexedCondition.value

            // update loop condition
            val conditionLoop = ConditionLoop(condition.condition, loop.instructions)
            conditionLoop.parent = loop.parent
            conditionLoop.indexInParent = loop.indexInParent
            replace(conditionLoop)

            // update parent
            conditionLoop.instructions.forEachIndexed { i, stmt ->
                if (stmt is Block){
                    stmt.parent = conditionLoop
                    stmt.indexInParent = i
                }
            }
        }
    }


}