package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.wasm.Index
import ir.wasm.WasmValueType

class MemoryGrow(val memoryId : Index, var amount: Expression) : Expression() {
    override fun clone(): Expression {
        return MemoryGrow(memoryId, amount.clone())
    }

    override fun getType(): List<WasmValueType> {
        return listOf(WasmValueType.i32)
    }

    override fun write(out: Appendable) {
        out.append("memory[$memoryId].grow(")
        amount.write(out)
        out.append(")")
    }

    override fun wat(wat: WatWriter) {
        amount.wat(wat)
        wat.writeLine("memory.grow $memoryId", this)
    }

    override fun visit(v: Visitor) {
        v.visit(amount){this.amount = it as Expression}
    }
}