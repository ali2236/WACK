package wasm

data class WasmElementSegment(val tableIndex: Index, val buffer: WasmBuffer, val functionIndexes: List<Index>)