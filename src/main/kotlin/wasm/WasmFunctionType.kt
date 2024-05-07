package wasm

import parser.WasmParamResult

data class WasmFunctionType(
    val index: Index,
    val params: MutableList<WasmValueType> = mutableListOf(),
    val result: MutableList<WasmValueType> = mutableListOf(),
) {
    fun getParamsAndResults(module: WasmModule): WasmParamResult {
        if (params.isEmpty() && result.isEmpty()) {
            val type = module.functionTypes.first { it.index == index }
            return WasmParamResult(type.params, type.result)
        } else {
            return WasmParamResult(params, result)
        }
    }
}