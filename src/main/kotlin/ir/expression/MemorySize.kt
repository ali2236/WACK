package ir.expression

import generation.WatWriter
import ir.wasm.Index
import ir.wasm.WasmValueType

class MemorySize(val memoryId: Index) : Expression() {
    override fun clone(): Expression {
        return MemorySize(memoryId)
    }

    override fun getType(): List<WasmValueType> {
        return listOf(WasmValueType.i32)
    }

    override fun write(out: Appendable) {
        out.append("memory[$memoryId].size")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("memory.size $memoryId", this)
    }
}