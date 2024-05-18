package ir.statement

import generation.WatWriter
import ir.expression.Expression

class BrIf(
    condition: Expression,
    val target: Block,
    val depth: Int,
) : If(
    condition,
    mutableListOf(Br(target, depth)),
    brackets = false,
) {

    val onTrue: Statement
        get() = instructions.first()
    override fun writeHeader(out: Appendable) {
        out.append("if(")
        condition.write(out)
        out.append(") br $depth")
    }

    override fun wat(wat: WatWriter) {
        condition.wat(wat)
        wat.writeLine("br_if $depth")
    }

}