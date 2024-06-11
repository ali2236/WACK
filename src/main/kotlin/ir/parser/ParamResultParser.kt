package ir.parser

import parser.WatParser
import ir.wasm.WasmValueType

data class WasmParamResult(val params: MutableList<WasmValueType>, val results: MutableList<WasmValueType>)

fun WatParser.Func_typeContext?.paramsAndResults(): WasmParamResult {
    val params = mutableListOf<WasmValueType>()
    val results = mutableListOf<WasmValueType>()
    this?.let {
        val hasParam = PARAM().isNotEmpty()
        val hasResult = RESULT().isNotEmpty()
        val valueTypes = value_type().toValueTypes()
        if (hasResult) {
            results.add(valueTypes.last())
        }
        if (hasParam) {
            val d = if (hasResult) 1 else 0
            val paramTypes = valueTypes.subList(0, valueTypes.size - d)
            params.addAll(paramTypes)
        }
    }
    return WasmParamResult(params, results)
}