package ast.expression

open class If(val condition: Expression, val trueBody: Expression, val elseBody: Expression? = null) : Expression() {
    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        trueBody.c(out)
        if(elseBody != null){
            out.append("else ")
            elseBody.c(out)
        }
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