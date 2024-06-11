package ir.wasm

data class WasmModule(
    val functionTypes: MutableList<WasmFunctionType> = mutableListOf(),
    val functions: MutableList<WasmFunction> = mutableListOf(),
    val tables: MutableList<WasmTable> = mutableListOf(),
    val memories: MutableList<WasmMemory> = mutableListOf(),
    val globals: MutableList<WasmGlobal> = mutableListOf(),
    val exports: MutableList<WasmExport> = mutableListOf(),
    val elementSegments: MutableList<WasmElementSegment> = mutableListOf(),
) {
    fun findOraddType(params: List<WasmValueType>, result: List<WasmValueType>): WasmFunctionType {
        var type = functionTypes
            .firstOrNull { type ->
                if (type.params.size != params.size || type.result.size != result.size) {
                    return@firstOrNull false
                }
                // params
                for (i in params.indices) {
                    if (params[i] != type.params[i]) {
                        return@firstOrNull false
                    }
                }

                // result
                for (i in result.indices) {
                    if (result[i] != type.result[i]) {
                        return@firstOrNull false
                    }
                }

                return@firstOrNull true
            }
        if (type == null) {
            type = WasmFunctionType(Index.next(functionTypes), params.toMutableList(), result.toMutableList())
            functionTypes.add(type)
        }
        return type
    }
}