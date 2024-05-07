package wasm

enum class WasmValueType {
    I32,
    I64,
    F32,
    F64,
    Unknown;

    companion object {
        fun parse(text: String) : WasmValueType{
            return valueOf(text.trim().uppercase())
        }
    }
}