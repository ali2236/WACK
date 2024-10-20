package ir.expression

import generation.WatWriter
import ir.Mode
import ir.wasm.WasmBitSign
import ir.wasm.WasmValueType

class CMPXCHG(
    val symbol: Load,
    val c3 : Expression,
    val c2 : Expression,
    val i : Expression,
    ) : Expression() {

    init {
        if(c3.exprType() != symbol.type) throw Exception()
        if(c2.exprType() != symbol.type) throw Exception()
        if(i.exprType() != WasmValueType.i32) throw Exception()
    }

    override fun clone(): Expression {
        return CMPXCHG(
            symbol.clone(),
            c3.clone(),
            c2.clone(),
            i.clone()
        ).also {
            it.id = this.id
        }
    }

    override fun exprType(): WasmValueType  = symbol.type

    override fun write(out: Appendable) {
        out.append("cmpxchg(")
        c3.write(out)
        out.append(", ")
        c2.write(out)
        out.append(", ")
        i.write(out)
        out.append(")")
    }

    override fun wat(wat: WatWriter) {
        i.wat(wat)
        c2.wat(wat)
        c3.wat(wat)
        val memSize = if(symbol.memorySize != null) "${symbol.memorySize}" else ""
        val memSign = if(symbol.sign != null && symbol.sign == WasmBitSign.u) "_u" else ""
        val ofst = if (symbol.offset != 0) " offset=${symbol.offset}" else ""
        val algn = if (symbol.align != 0) " align=${symbol.align}" else ""
        val memIndex = if(WAPC.params!!.multipleMemories) " ${symbol.memoryIndex}" else ""
        wat.writeLine("${symbol.type.name}.atomic.rmw$memSize$memSign.cmpxchg$memIndex$ofst$algn")
    }
}