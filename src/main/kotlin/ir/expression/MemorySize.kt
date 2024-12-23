package ir.expression

import compiler.WAPC
import generation.c.CWriter
import generation.wat.WatWriter
import ir.wasm.Index
import ir.wasm.WasmValueType

class MemorySize(val memoryId: Index) : Expression() {
    override fun clone(): Expression {
        return MemorySize(memoryId)
    }

    override fun exprType(): WasmValueType {
        return WasmValueType.i32
    }

    override fun write(out: Appendable) {
        out.append("memory[$memoryId].size")
    }

    override fun wat(wat: WatWriter) {
        val idx = if(WAPC.params.multipleMemories) " $memoryId" else ""
        wat.writeLine("memory.size$idx", this)
    }

    override fun c(writer: CWriter) {
        writer.writeLine("wasm_rt_memory_size()")
    }
}