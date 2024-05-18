package wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import parser.WasmParamResult

data class WasmFunctionType(
    val index: Index,
    val params: MutableList<WasmValueType> = mutableListOf(),
    val result: MutableList<WasmValueType> = mutableListOf(),
) : WebAssemblyInstruction {
    fun getParamsAndResults(module: WasmModule): WasmParamResult {
        if (params.isEmpty() && result.isEmpty()) {
            val type = module.functionTypes.first { it.index == index }
            return WasmParamResult(type.params, type.result)
        } else {
            return WasmParamResult(params, result)
        }
    }

    fun paramsWat() : String{
        return  if(params.isNotEmpty()) " (param ${params.joinToString(" ")})" else ""
    }

    fun resultWat() : String {
        return  if(result.isNotEmpty()) " (result ${params.joinToString(" ")})" else ""
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("(type (;$index;) (func${paramsWat()}${resultWat()}))")
    }
}