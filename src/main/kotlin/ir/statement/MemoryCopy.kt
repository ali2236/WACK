package ir.statement

import generation.WatWriter
import ir.Mode
import ir.expression.Expression
import ir.finder.Visitor
import ir.wasm.Index

class MemoryCopy(
    val fromMemoryIndex: Index,
    val toMemoryIndex: Index,
    var n: Expression,
    var i1: Expression,
    var i2: Expression,
) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("memory[$fromMemoryIndex].copy(memory[$toMemoryIndex], ")
        i1.write(out)
        out.append(", ")
        i2.write(out)
        out.append(", ")
        n.write(out)
        out.append(");\n")
    }

    override fun wat(wat: WatWriter) {
        i1.wat(wat)
        i2.wat(wat)
        n.wat(wat)
        val indexes = if (Mode.multipleMemories) " $fromMemoryIndex $toMemoryIndex" else ""
        wat.writeLine("memory.copy$indexes", this)
    }

    override fun visit(v: Visitor) {
        v.visit(n) { n = it as Expression }
        v.visit(i1) { i1 = it as Expression }
        v.visit(i2) { i2 = it as Expression }
    }
}