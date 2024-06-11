package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmTable(val index: Index, val min: Int, val max: Int?, val refType : WasmRefType) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.writeLine("(table (;$index;) $min $max funcref)")
    }

}