package ir.wasm

enum class WasmValueType {
    i32,
    i64,
    f32,
    f64,
    Unknown;

    fun cType(): String {
        return when (this) {
            i32 -> "int"
            i64 -> "long"
            f32 -> "float"
            f64 -> "double"
            Unknown -> "void*"
        }
    }

    fun defaultValue(): String {
        return when (this) {
            i32, i64 -> "0"
            f32, f64 -> "0.0"
            Unknown -> "NULL"
        }
    }

    fun byteCount(): Int {
        return when (this) {
            i32 -> 4
            i64 -> 8
            f32 -> 4
            f64 -> 8
            Unknown -> 4
        }
    }

    fun lb(): String {
        return when (this) {
            i32 -> Int.MIN_VALUE
            i64 -> Long.MIN_VALUE
            f32 -> Float.MIN_VALUE
            f64 -> Double.MIN_VALUE
            Unknown -> Double.NaN
        }.toString()
    }

    fun ub(): String {
        return when (this) {
            i32 -> Int.MAX_VALUE
            i64 -> Long.MAX_VALUE
            f32 -> Float.MAX_VALUE
            f64 -> Double.MAX_VALUE
            Unknown -> Double.NaN
        }.toString()
    }

    companion object {
        fun parse(text: String): WasmValueType {
            return valueOf(text.trim().lowercase())
        }
    }
}