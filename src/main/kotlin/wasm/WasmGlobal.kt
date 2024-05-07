package wasm

data class WasmGlobal(val type: WasmGlobalType, val buffer: WasmBuffer)

data class WasmGlobalType(val type: WasmValueType, val mutable: Boolean)

