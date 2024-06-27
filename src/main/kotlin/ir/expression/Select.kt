package ir.expression

import generation.WatWriter
import ir.wasm.WasmValueType

// return selector == 0 ? val2 : val1
// TODO: implementation maybe incorrect
class Select(val val1: Expression, val val2: Expression, val selector: Expression, val resultType: WasmValueType?) :
    Expression() {
    override fun clone(): Expression {
        return Select(val1.clone(), val2.clone(), selector.clone(), resultType)
    }

    override fun exprType(): WasmValueType {
        return resultType ?: val1.exprType()
    }

    override fun write(out: Appendable) {
        selector.write(out)
        out.append(" ? ")
        val1.write(out)
        out.append(" : ")
        val2.write(out)
    }

    override fun wat(wat: WatWriter) {
        val1.wat(wat)
        val2.wat(wat)
        selector.wat(wat)
        val type = if(resultType != null) " (result $resultType)" else ""
        wat.writeLine("select$type", this)
    }

}