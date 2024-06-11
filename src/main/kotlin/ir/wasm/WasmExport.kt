package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmExport(val name: String, val kind: WasmExportKind, val index: Index) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.writeLine("(export $name ($kind $index))")
    }

}

