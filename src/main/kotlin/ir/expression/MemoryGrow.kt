package ir.expression

import compiler.WAPC
import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor
import ir.wasm.Index
import ir.wasm.WasmValueType

class MemoryGrow(val memoryId : Index, var amount: Expression) : Expression() {
    override fun clone(): Expression {
        return MemoryGrow(memoryId, amount.clone())
    }

    override fun exprType(): WasmValueType {
        return WasmValueType.i32
    }

    override fun write(out: Appendable) {
        out.append("memory[$memoryId].grow(")
        amount.write(out)
        out.append(")")
    }

    override fun wat(wat: WatWriter) {
        amount.wat(wat)
        val idx = if(WAPC.params.multipleMemories) " $memoryId" else ""
        wat.writeLine("memory.grow$idx", this)
    }

    override fun c(writer: CWriter) {
        writer.write("wasm_rt_memory_grow(")
        amount.c(writer)
        writer.write(");")
        writer.endLine()
    }

    override fun visit(v: Visitor) {
        v.visit(amount){this.amount = it as Expression}
    }
}