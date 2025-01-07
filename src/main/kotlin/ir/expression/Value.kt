package ir.expression

import generation.wat.WatWriter
import ir.statement.Statement
import ir.wasm.WasmValueType
import java.lang.Exception

open class Value(val type: WasmValueType, var value: String) : ImmutableExpression() {
    override fun exprType(): WasmValueType {
        return type
    }

    override fun write(out: Appendable) {
        out.append(value)
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("${type}.const $value", this)
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

    fun add(i: Long): Value {
        return when (type) {
            WasmValueType.i32 -> Value(type, (value.toInt() + i).toString())
            WasmValueType.i64 -> Value(type, (value.toLong() + i).toString())
            WasmValueType.f32 -> Value(type, (value.toFloat() + i).toString())
            WasmValueType.f64 -> Value(type, (value.toDouble() + i).toString())
            //WasmValueType.Unknown -> throw Error("Type $type unknown to add")
        }
    }

    fun add(i: Value): Value {
        return when (i.type) {
            WasmValueType.i32 -> add(i.value.toLong())
            WasmValueType.i64 -> add(i.value.toLong())
            else -> throw Exception("should use an integer add")
        }
    }

    fun multiply(i: Long): Value {
        return when (type) {
            WasmValueType.i32 -> Value(type, (value.toInt() * i).toString())
            WasmValueType.i64 -> Value(type, (value.toLong() * i).toString())
            WasmValueType.f32 -> Value(type, (value.toFloat() * i).toString())
            WasmValueType.f64 -> Value(type, (value.toDouble() * i).toString())
            //WasmValueType.Unknown -> throw Error("Type $type unknown to multiply")
        }
    }

    fun multiply(i: Value): Value {
        return when (i.type) {
            WasmValueType.i32 -> multiply(i.value.toLong())
            WasmValueType.i64 -> multiply(i.value.toLong())
            else -> throw Exception("should use an integer add")
        }
    }

    fun cast(toType: WasmValueType): Statement {
        return Value(toType, toType.fromNumber(type.number(value)))
    }

    fun toInt(): Int {
        return value.toInt()
    }

    companion object {
        fun i32(i: Int): Value {
            return Value(WasmValueType.i32, "$i")
        }

        val zero: Value = Value(WasmValueType.i32, "0")
        val one: Value = Value(WasmValueType.i32, "1")
    }


}