package wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmTable(val min: Int, val max: Int, val shared: Boolean) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.writeLine("(table $min $max funcref)")
    }

}