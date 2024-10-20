package ir.expression

import generation.WatWriter
import ir.Mode
import ir.wasm.Index
import ir.wasm.WasmValueType

class AtomicWait(
    val type: WasmValueType,
    val memoryIndex: Index,
    val offset: Int = 0,
    val align: Int = 0,
    val k : Expression,
    val n: Expression,
    val i: Expression,
    ) : Expression() {

    init {
        if (k.exprType() != WasmValueType.i64){
            throw Exception()
        }
        if(n.exprType() != type){
            throw Exception()
        }
        if(i.exprType() != WasmValueType.i32){
            throw Exception()
        }
    }

    override fun clone(): Expression {
        return AtomicWait(
            type,
            memoryIndex,
            offset,
            align,
            k.clone(),
            n.clone(),
            i.clone()
        ).also {
            it.id = this.id
        }
    }

    override fun exprType(): WasmValueType = WasmValueType.i32

    override fun write(out: Appendable) {
        out.append("wait(")
        k.write(out)
        n.write(out)
        i.write(out)
        out.append(")")
    }

    override fun wat(wat: WatWriter) {
        i.wat(wat)
        n.wat(wat)
        k.wat(wat)
        val ofst = if (offset != 0) " offset=$offset" else ""
        val algn = if (align != 0) " align=$align" else ""
        val memIndex = if(WAPC.params!!.multipleMemories) " $memoryIndex" else ""
        wat.writeLine("memory.atomic.wait${type.byteCount() * 8}${memIndex}$ofst$algn", this)
    }
}