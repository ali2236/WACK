package ir.statement

import compiler.WAPC
import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor
import ir.wasm.Index

class MemoryFill(
    val memoryId: Index,
    var i: Expression,
    var value: Expression,
    var n: Expression,
) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("memory[$memoryId].fill(")
        i.write(out)
        out.append(", ")
        value.write(out)
        out.append(", ")
        n.write(out)
        out.append(");\n")
    }

    override fun wat(wat: WatWriter) {
        i.wat(wat)
        value.wat(wat)
        n.wat(wat)
        val index = if(WAPC.params.multipleMemories) " $memoryId" else ""
        wat.writeLine("memory.fill$index", this)
    }

    override fun visit(v: Visitor) {
        v.visit(i) { this.i = it as Expression }
        v.visit(value) { this.value = it as Expression }
        v.visit(n) { this.n = it as Expression }
    }
}