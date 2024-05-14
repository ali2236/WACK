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
){

    override val trueBody: Statement
        get() = instructions.first()

}