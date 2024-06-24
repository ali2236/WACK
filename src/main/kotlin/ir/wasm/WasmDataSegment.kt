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
        wat.write("(data (;$dataIndex;)")
        if(constExpr.isNotEmpty()) {
            wat.write(" (")
            wat.endLine()
            wat.indent++
            wat.writeAll(constExpr)
            wat.indent--
            wat.startLine()
            wat.write(")")
        }
        wat.write(" $bytes)")
        wat.endLine()

    }
}