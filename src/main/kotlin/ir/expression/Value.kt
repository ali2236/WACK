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

    fun add(i : Int): Value {
        return when(type){
            WasmValueType.i32 -> Value(type, (value.toInt() + i).toString())
            WasmValueType.i64 -> Value(type, (value.toLong() + i).toString())
            WasmValueType.f32 -> Value(type, (value.toFloat() + i).toString())
            WasmValueType.f64 -> Value(type, (value.toDouble() + i).toString())
            WasmValueType.Unknown -> throw Error("Type $type unknown to add")
        }
    }


}