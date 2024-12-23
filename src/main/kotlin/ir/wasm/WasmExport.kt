package ir.wasm

import generation.wat.WatWriter
import generation.WebAssemblyInstruction

data class WasmExport(val name: String, val kind: WasmExportKind, val index: Index) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        val indexValue = index.access(kind.typeName())
        wat.writeLine("(export $name ($kind $indexValue))")
    }

}

