package wasm

data class WasmModule(
    val functionTypes: MutableList<WasmFunctionType> = mutableListOf(),
    val functions: MutableList<WasmFunction> = mutableListOf(),
    val tables: MutableList<WasmTable> = mutableListOf(),
    val memories: MutableList<WasmMemory> = mutableListOf(),
    val globals: MutableList<WasmGlobal> = mutableListOf(),
    val exports: MutableList<WasmExport> = mutableListOf(),
    val elementSegments: MutableList<WasmElementSegment> = mutableListOf(),
)