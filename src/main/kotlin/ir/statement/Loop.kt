package ir.statement

import generation.WatWriter

open class Loop(instructions: MutableList<Statement> = mutableListOf()) : Block(instructions) {

    fun breakConditions(): List<IndexedValue<BrIf>> {
        return instructions.withIndex()
            .filter { it.value is BrIf }
            .filterIsInstance<IndexedValue<BrIf>>()
    }

    override fun writeHeader(out: Appendable) {
        out.append("loop")
    }

    override fun watHeader(wat: WatWriter) {
        wat.writeLine("loop${watBlockType()}")
    }
}