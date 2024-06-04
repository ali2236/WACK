package ir.expression

import generation.WatWriter
import wasm.Index

class MemorySize(val memoryId: Index) : Expression() {
    override fun clone(): Expression {
        return MemorySize(memoryId)
    }

    override fun write(out: Appendable) {
        out.append("memory[$memoryId].size")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("memory.size $memoryId")
    }
}