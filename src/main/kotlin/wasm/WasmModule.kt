package wasm

data class WasmModule(
    val functionTypes: MutableList<WasmFunctionType> = mutableListOf(),
    val functions: MutableList<WasmFunction> = mutableListOf(),
    val tables: MutableList<WasmTable> = mutableListOf(),
    val memories: MutableList<WasmMemory> = mutableListOf(),
    val globals: MutableList<WasmGlobal> = mutableListOf(),
    val exports: MutableList<WasmExport> = mutableListOf(),
    val elementSegments: MutableList<WasmElementSegment> = mutableListOf(),
) {
    fun addType(params: List<WasmValueType>, result: List<WasmValueType>): WasmFunctionType {
        var type = functionTypes
            .firstOrNull { type ->
                type.params.containsAll(params) && params.containsAll(type.params)
                        && type.result.containsAll(result) && result.containsAll(type.result)
            }
        if (type == null) {
            type = WasmFunctionType(Index.next(functionTypes), params.toMutableList(), result.toMutableList())
            functionTypes.add(type)
        }
        return type
    }
}