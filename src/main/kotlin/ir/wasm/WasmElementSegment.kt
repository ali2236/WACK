package ir.wasm

import generation.wat.WatWriter
import generation.WebAssemblyInstruction
import ir.Names
import ir.statement.Statement

data class WasmElementSegment(
    val elementIndex: Index,
    val tableIndex: Index,
    val offset: List<Statement>,
    val functionIndexes: List<Index>,
) :
    WebAssemblyInstruction {
    val funIndxs = if(functionIndexes.isNotEmpty()) " func ${functionIndexes.map { it.access(Names.function) }.joinToString(" ")}" else ""
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