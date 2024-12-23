package ir.statement

import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

class BrIf(
    condition: Expression,
    target: Block,
    val depth: Int,
    var result: Expression? = null
) : If(
    condition,
    mutableListOf(Br(target, depth, result)),
    brackets = false,
) {

    val onTrue: Statement
        get() = instructions.first()
    override fun writeHeader(out: Appendable) {
        out.append("if(")
        condition.write(out)
        out.append(") br $depth $result")
    }

    override fun wat(wat: WatWriter) {
        result?.wat(wat)
        condition.wat(wat)
        wat.writeLine("br_if $depth", this)
    }

    override fun visit(v: Visitor) {
        if(result != null) v.visit(result!!){this.result = it as Expression?}
        super.visit(v)
    }
}