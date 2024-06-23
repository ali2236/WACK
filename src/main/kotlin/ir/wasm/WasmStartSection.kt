package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

class WasmStartSection(val functionIndex: Index) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.writeLine("(start $functionIndex)")
    }
}