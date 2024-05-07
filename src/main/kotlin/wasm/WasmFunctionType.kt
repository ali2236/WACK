package wasm

data class WasmFunctionType(
    val index: Index,
    val params: MutableList<WasmValueType> = mutableListOf(),
    val result: MutableList<WasmValueType> = mutableListOf(),
)