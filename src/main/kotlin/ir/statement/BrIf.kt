package ir.statement

import ir.expression.Expression

class BrIf(
    condition: Expression,
    trueBody: Statement,
    val depth: Int,
) : If(
    condition,
    trueBody
)