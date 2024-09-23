package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.wasm.WasmValueType

class Convert(val toType: WasmValueType, val instruction: String, var value: Expression) : Expression() {
    override fun clone(): Expression {
        return Convert(toType, instruction, value.clone())
    }

    override fun exprType(): WasmValueType {
        return toType
    }

    override fun write(out: Appendable) {
        out.append("(${toType.cType()})")
        value.write(out)
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        wat.writeLine("${toType}.${instruction}", this)
    }

    override fun visit(v: Visitor) {
        v.visit(value) {this.value = it as Expression}
    }
}