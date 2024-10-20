package ir.expression

import WAPC
import generation.WatWriter
import ir.Mode
import ir.wasm.Index
import ir.wasm.WasmValueType

class AtomicNotify(
    val memoryIndex: Index,
    val offset: Int = 0,
    val align: Int = 0,
    val k : Expression,
    val i : Expression,
) : Expression() {

    init {
        if(k.exprType() != WasmValueType.i32){
            throw Exception()
        }

        if(i.exprType() != WasmValueType.i32){
            throw Exception()
        }
    }

    override fun clone(): Expression {
        return AtomicNotify(
            memoryIndex,
            offset,
            align,
            k.clone(),
            i.clone(),
        ).also {
            it.id = this.id
        }
    }

    override fun exprType(): WasmValueType = WasmValueType.i32

    override fun write(out: Appendable) {
        out.append("notify(")
        k.write(out)
        out.append(", ")
        i.write(out)
        out.append(")")
    }

    override fun wat(wat: WatWriter) {
        i.wat(wat)
        k.wat(wat)
        val ofst = if (offset != 0) " offset=$offset" else ""
        val algn = if (align != 0) " align=$align" else ""
        val memIndex = if(WAPC.params!!.multipleMemories) " $memoryIndex" else ""
        wat.writeLine("memory.atomic.notify${memIndex}$ofst$algn", this)
    }
}