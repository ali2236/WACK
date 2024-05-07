package ir.expression

class FunctionCall(
    val name: String,
    val params: List<Expression>,
    val hasReturn: Boolean,
) : Expression() {
    override fun c(out: Appendable) {
        out.append(name)
        out.append("(")

        val paramCount = params.size
        for (i in 0 until paramCount) {
            val param = params[i]
            param.c(out)
            if (i != paramCount - 1) {
                out.append(", ")
            }
        }

        out.append(")")
        if (!hasReturn) {
            out.append(";\n")
        }
    }

}