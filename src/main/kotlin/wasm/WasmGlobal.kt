package wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmGlobal(val index: Index, val type: WasmGlobalType, val buffer: WasmBuffer) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.writeLine("(global (;$index;) ($type) ($buffer))")
    }

}

data class WasmGlobalType(val type: WasmValueType, val mutable: Boolean){
    override fun toString(): String {
        val mut = if(mutable) "mut " else ""
        return "$mut$type"
    }
}

