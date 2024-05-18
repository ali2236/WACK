package ir.expression

import generation.WatWriter
import wasm.WasmValueType

open class Value(val type: WasmValueType, val value: String) : Expression() {
    override fun write(out: Appendable) {
        out.append(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Value) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("const.$type $value")
    }


}