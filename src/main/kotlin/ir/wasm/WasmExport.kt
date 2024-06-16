package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.Mode

data class WasmExport(val name: String, val kind: WasmExportKind, val index: Index) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        val indexValue = if (kind == WasmExportKind.func && !Mode.callByIndex) {
            "\$f$index"
        } else {
            index.toString()
        }
        wat.writeLine("(export $name ($kind $indexValue))")
    }

}

