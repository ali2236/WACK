package ir.expression

class If(val condition: Expression, val trueBody: Block) : Expression() {
    override fun c(out: Appendable) {
        out.append("if(")
        condition.c(out)
        out.append(")")
        trueBody.c(out)
    }

}