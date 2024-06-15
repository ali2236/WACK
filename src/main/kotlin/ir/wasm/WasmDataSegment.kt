package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.statement.Statement

data class WasmDataSegment(
    val dataIndex : Index,
    val memoryIndex: Index,
    val constExpr: List<Statement>,
    val bytes: String
) :
    WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.startLine()
        wat.write("(data (;$dataIndex;) (")
        wat.endLine()
        wat.indent++
        wat.writeAll(constExpr)
        wat.indent--
        wat.startLine()
        wat.write(") $bytes)")
        wat.endLine()

    }
}