package ir.expression

import generation.WatWriter
import ir.wasm.Index
import ir.wasm.WasmValueType

class IndirectFunctionCall(
    val tableIndex: Index,
    val typeIndex: Index,
    val functionIndex: Expression,
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
        params.forEachIndexed { i, it ->
            it.write(out)
            if (i != params.size - 1) {
                out.append(",")
            }
        }
        out.append(")")
        if (returnType.isEmpty()) {
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params.asReversed())
        functionIndex.wat(wat)
        wat.writeLine("call_indirect $tableIndex (type $typeIndex)", this)
    }
}