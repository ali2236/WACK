package wasm

data class WasmDataSegment(val memoryIndex: Index, val offset: WasmBuffer, val bytes: WasmBuffer)