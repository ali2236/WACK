package ir.expression

import generation.c.CWriter
import generation.wat.WatWriter
import ir.wasm.WasmValueType

class FunctionResult(val type: WasmValueType) : ImmutableExpression() {

    override fun exprType(): WasmValueType {
        return type
    }

    override fun write(out: Appendable) {
        out.append("result_$type")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("nop", this)
    }

    override fun c(writer: CWriter) {

    }
}