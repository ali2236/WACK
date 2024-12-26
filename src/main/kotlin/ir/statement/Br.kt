package ir.statement

import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

class Br(val target: Block, var depth: Int, var result: Expression? = null) : BasicStatement() {
    override fun write(out: Appendable) {
        val resultValue = if (result != null) " $result" else ""
        if (target is Loop && depth == 0) {
            out.append("continue$resultValue;\n")
        } else if (target is Block && depth == 0) {
            out.append("break $resultValue;\n")
        } else {
            out.append("Br $depth $resultValue;\n")
        }
    }

    override fun wat(wat: WatWriter) {
        result?.wat(wat)
        wat.writeLine("br $depth", this)
    }

    override fun visit(v: Visitor) {
        if (result != null){
            v.visit(result!!) {this.result = it as Expression?}
        }
    }
}