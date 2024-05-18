package wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmElementSegment(
    val tableIndex: Index,
    val buffer: WasmBuffer,
    val functionIndexes: List<Index>,
) :
    WebAssemblyInstruction {
    val funIndxs = if(functionIndexes.isNotEmpty()) " func ${functionIndexes.joinToString(" ")}" else ""
    override fun wat(wat: WatWriter) {
        wat.writeLine("(elem (;0;) ($buffer)$funIndxs)")
    }

}