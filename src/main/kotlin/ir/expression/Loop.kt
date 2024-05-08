package ir.expression

class Loop(val body: Block) : Expression() {
    fun conditions(): List<IndexedValue<BrIf>> {
        val conditions = body.instructions.withIndex()
            .filter { it.value is BrIf }
            .filterIsInstance<IndexedValue<BrIf>>()
            .filter { it.value.depth == 0 }
        return conditions
    }
    override fun c(out: Appendable) {
        // search for break condition
        // WARNING: this could break code if you don't check dependence better


        // condition
        /*if (conditions.isNotEmpty()) {
            val indexedCondition = conditions.last()
            val condition = indexedCondition.value
            val invertedCondition = BrIf(condition.condition, condition.trueBody, condition.depth).condition

            // print condition here
            invertedCondition.c(out)

            // remove original condition from code
            body.instructions.removeAt(indexedCondition.index)
        }*/
        // end search
        out.append("while(1)")
        body.c(out)
    }
}