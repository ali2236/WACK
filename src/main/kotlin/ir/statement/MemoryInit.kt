package ir.statement

import compiler.WAPC
import generation.WatWriter
import ir.expression.Expression
import ir.wasm.Index

// copy data section into memory
class MemoryInit(
    val memoryId: Index,
    val dataSection: Index,
    val n: Expression,
    val s: Expression,
    val d: Expression,
) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("M[$memoryId].init(Data[$dataSection], ")
        n.write(out)
        out.append(", ")
        s.write(out)
        out.append(", ")
        d.write(out)
        out.append(");\n")
    }

    override fun wat(wat: WatWriter) {
        d.wat(wat)
        s.wat(wat)
        n.wat(wat)
        val index = if(WAPC.params!!.multipleMemories) " $memoryId" else ""
        wat.writeLine("memory.init$index $dataSection", this)
    }
}