package ir.expression

open class Loop(var condition: Expression = Value("1")) : Block() {

    fun breakConditions(): List<IndexedValue<BrIf>> {
        return instructions.withIndex()
            .filter { it.value is BrIf }
            .filterIsInstance<IndexedValue<BrIf>>()
    }
    override fun c(out: Appendable) {
        // search for break condition
        // WARNING: this could break code if you don't check dependence better


        // condition
        /**/
        // end search
        out.append("while(")
        condition.c(out)
        out.append(")")
        super.c(out)
    }

    override fun close() {
        instructions.add(Break())
    }
}