package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.Mode
import ir.statement.Statement

data class WasmElementSegment(
    val elementIndex: Index,
    val tableIndex: Index,
    val offset: List<Statement>,
    val functionIndexes: List<Index>,
) :
    WebAssemblyInstruction {
    val funIndxs = if(functionIndexes.isNotEmpty()) " func ${functionIndexes.map { if(Mode.callByIndex) "$it" else "\$f$it" }.joinToString(" ")}" else ""
    override fun wat(wat: WatWriter) {
        val index = elementIndex
        wat.startLine()
        wat.write("(elem (;$index;) (table $tableIndex) (")
        wat.endLine()
        wat.indent++
        wat.writeAll(offset)
        wat.indent--
        wat.writeLine(")$funIndxs)")
    }

}