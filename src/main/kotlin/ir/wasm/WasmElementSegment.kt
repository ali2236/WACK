package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmElementSegment(
    val tableIndex: Index,
    val offset: Int,
    val functionIndexes: List<Index>,
) :
    WebAssemblyInstruction {
    val funIndxs = if(functionIndexes.isNotEmpty()) " func ${functionIndexes.joinToString(" ")}" else ""
    override fun wat(wat: WatWriter) {
        wat.writeLine("(elem (;0;) (table $tableIndex) (i32.const $offset)$funIndxs)")
    }

}