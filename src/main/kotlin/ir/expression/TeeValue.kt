package ir.expression

import generation.WatWriter
import ir.wasm.WasmValueType

open class TeeValue(val expr: Expression) : Expression() {
    override fun clone(): Expression {
        return TeeValue(expr.clone())
    }

    override fun getType(): List<WasmValueType> {
        return expr.getType()
    }

    override fun write(out: Appendable) {
        expr.write(out)
    }

    override fun wat(wat: WatWriter) {
       // dont pass
    }
}