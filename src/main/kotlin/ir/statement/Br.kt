package ir.statement

import generation.WatWriter
import ir.expression.Expression

class Br(val target: Block, var depth: Int, val result: Expression? = null) : BasicStatement() {
    override fun write(out: Appendable) {
        val resultValue = if (result != null) " $result" else ""
        if (target is Loop && depth == 0) {
            out.append("continue$resultValue;\n")
        } else if (target is Block && depth == 0) {
            out.append("break$resultValue;\n")
        } else {
            out.append("Br $depth$resultValue")
        }
    }

    override fun wat(wat: WatWriter) {
        result?.wat(wat)
        wat.writeLine("br $depth", this)
    }
}