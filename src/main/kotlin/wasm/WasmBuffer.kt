package wasm

import generation.WatWriter
import generation.WebAssemblyBlock
import generation.WebAssemblyInstruction
import ir.statement.Statement
import parser.WatParser

class WasmBuffer(val instructions: MutableList<Statement>) : WebAssemblyInstruction {

    override fun wat(wat: WatWriter) {
        TODO()
    }

}