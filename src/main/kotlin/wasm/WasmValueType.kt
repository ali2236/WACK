package wasm

enum class WasmValueType {
    I32,
    I64,
    F32,
    F64,
    Unknown;

    fun cType() : String{
        return when(this){
            I32 -> "int"
            I64 -> "long"
            F32 -> "float"
            F64 -> "double"
            Unknown -> "void*"
        }
    }

    fun defaultValue(): String {
        return when(this){
            I32, I64 -> "0"
            F32, F64 -> "0.0"
            Unknown -> "NULL"
        }
    }

    fun byteCount(): Int {
        return when(this){
            I32 -> 4
            I64 -> 8
            F32 -> 4
            F64 -> 8
            Unknown -> 4
        }
    }

    companion object {
        fun parse(text: String) : WasmValueType{
            return valueOf(text.trim().uppercase())
        }
    }
}