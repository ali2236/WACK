package ir.statement

import ir.expression.Expression

class BrIf(
    condition: Expression,
    trueBody: Statement,
    val depth: Int,
) : If(
    condition,
    mutableListOf(trueBody),
    brackets = false,
){

    override val trueBody: Statement
        get() = instructions.first()

}