package ir.statement

open class Loop : Block() {

    fun breakConditions(): List<IndexedValue<BrIf>> {
        return instructions.withIndex()
            .filter { it.value is BrIf }
            .filterIsInstance<IndexedValue<BrIf>>()
    }
    override fun write(out: Appendable) {
        out.append("loop ")
        super.write(out)
    }

    override fun close() {
//         push(End())
    }
}