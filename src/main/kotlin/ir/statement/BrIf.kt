package ir.statement

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

    override fun writeHeader(out: Appendable) {
        out.append("if(")
        condition.write(out)
        out.append(") br $depth")
    }

    val onTrue: Statement
        get() = instructions.first()

}