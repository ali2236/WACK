package ir.statement

import ir.wasm.WasmValueType

open class Loop(instructions: MutableList<Statement> = mutableListOf(), type: WasmValueType? = null) : Block(instructions, type = type) {

    fun breakConditions(): List<IndexedValue<BrIf>> {
        return instructions.withIndex()
            .filter { it.value is BrIf }
            .filterIsInstance<IndexedValue<BrIf>>()
    }

    override fun writeHeader(out: Appendable) {
        out.append("loop")
    }

    override val blockName: String
        get() = "loop"
}