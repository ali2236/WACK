package ir.expression

import generation.WatWriter
import ir.wasm.Index
import ir.wasm.WasmValueType

class IndirectFunctionCall(
    val tableIndex : Index,
    val typeIndex : Index,
    val functionIndex : Expression,
    val params: List<Expression>,
    val returnType: List<WasmValueType>
) : Expression() {
    override fun clone(): Expression {
        return IndirectFunctionCall(tableIndex, typeIndex, functionIndex, params.map { it.clone() }, returnType)
    }

    override fun getType(): List<WasmValueType> {
        return returnType
    }

    override fun write(out: Appendable) {
        out.append("T[$tableIndex][$functionIndex](")
        params.forEach {
            it.write(out)
        }
        out.append(")")
        if(returnType.isEmpty()){
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params)
        functionIndex.wat(wat)
        wat.writeLine("call_indirect $tableIndex (type $typeIndex)", this)
    }
}