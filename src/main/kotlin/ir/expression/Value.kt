package ir.expression

import generation.WatWriter
import wasm.WasmValueType

open class Value(val type: WasmValueType, val value: String) : ImmutableExpression() {
    override fun write(out: Appendable) {
        out.append(value)
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("${type}.const $value")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Value) return false

        if (type != other.type) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }


}