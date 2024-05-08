package ir.expression

open class If(val condition: Expression, val trueBody: Expression) : Expression() {
    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        trueBody.c(out)
    }

}

class BrIf(
    condition: Expression,
    trueBody: Expression,
    val depth: Int,
) : If(
    condition,
    trueBody
)