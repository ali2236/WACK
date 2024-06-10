package wasm

data class WasmDataSegment(val memoryIndex: Index, val offset: Int, val bytes: List<UInt>)