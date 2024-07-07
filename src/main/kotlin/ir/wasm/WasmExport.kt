package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.Mode
import ir.Names

data class WasmExport(val name: String, val kind: WasmExportKind, val index: Index) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        val indexValue = index.access(kind.typeName())
        wat.writeLine("(export $name ($kind $indexValue))")
    }

}

