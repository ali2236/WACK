package ir.expression

import generation.WatWriter
import ir.Mode
import ir.wasm.WasmBitSign
import ir.wasm.WasmValueType

class AtomicOp(
    val load: Load,
    val operator: Operator,
    val c2: Expression,
    val i: Expression,
) : Expression() {

    init {
        if (c2.exprType() != load.type) throw Exception()
        if (i.exprType() != WasmValueType.i32) throw Exception()
    }

    override fun clone(): Expression {
        return AtomicOp(
            load.clone(),
            operator.copy(),
            c2.clone(),
            i.clone(),
        ).also { it.id = this.id }
    }

    override fun exprType(): WasmValueType = load.type

    override fun write(out: Appendable) {
        out.append("atomic_${operator.name}($c2, $i)")
    }

    override fun wat(wat: WatWriter) {
        i.wat(wat)
        c2.wat(wat)
        val memSize = if(load.memorySize != null) "${load.memorySize}" else ""
        val memSign = if(load.sign != null && load.sign == WasmBitSign.u) "_u" else ""
        val ofst = if (load.offset != 0) " offset=${load.offset}" else ""
        val algn = if (load.align != 0) " align=${load.align}" else ""
        val memIndex = if(WAPC.params!!.multipleMemories) " ${load.memoryIndex}" else ""
        wat.writeLine("${load.type.name}.atomic.rmw$memSize$memSign.${operator.name}$memIndex$ofst$algn")
    }

    data class Operator(val name: String){
        companion object {
            val add = Operator("add")
            val sub = Operator("sub")
            val and = Operator("and")
            val or = Operator("or")
            val xor = Operator("xor")
            val xchg = Operator("xchg")
        }
    }
}