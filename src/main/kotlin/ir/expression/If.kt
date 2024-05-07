package ir.expression

open class If(val condition: Expression, val trueBody: Block) : Expression() {
    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        trueBody.c(out)
    }

}

class BrIf(
    condition: Expression,
    trueBody: Block
) : If(
    if(condition is BinaryOP) condition.invert() else condition,
    trueBody
)