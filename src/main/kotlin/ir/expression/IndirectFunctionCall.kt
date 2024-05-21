package ir.expression

import generation.WatWriter
import wasm.Index

class IndirectFunctionCall(
    val tableIndex : Index,
    val typeIndex : Index,
    val functionIndex : Expression,
    val params: List<Expression>,
    val hasReturn: Boolean,
) : Expression() {
    override fun write(out: Appendable) {
        out.append("T[$tableIndex][$functionIndex](")
        params.forEach {
            it.write(out)
        }
        out.append(")")
        if(!hasReturn){
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params)
        functionIndex.wat(wat)
        wat.writeLine("call_indirect $tableIndex (type $typeIndex)")
    }
}