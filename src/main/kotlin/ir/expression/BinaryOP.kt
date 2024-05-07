package ir.expression

class BinaryOP(val operator: Operator, val first: Expression, val second: Expression) : Expression() {
    override fun c(out: Appendable) {
        if (first is BinaryOP) out.append("(")
        first.c(out)
        if (first is BinaryOP) out.append(")")

        out.append(operator.sign)
        if (second is BinaryOP) out.append("(")
        second.c(out)
        if (second is BinaryOP) out.append(")")
    }

    fun invert(): Expression {
        val invertedOperator = Operator(operator.invertSign, operator.sign)
        return BinaryOP(invertedOperator, first, second)
    }
}

data class Operator(val sign: String, val invertSign: String) {
    companion object {
        val eq = Operator("==", "!=")
        val neq = Operator("!=", "==")
        val lt = Operator("<", ">")
        val le = Operator("<=", ">=")
        val gt = Operator(">", "<")
        val ge = Operator(">=", "<=")
        val add = Operator("+", "+")
        val sub = Operator("-", "-")
        val div = Operator("/", "/")
        val mul = Operator("*", "*")
        val shl = Operator("<<", "<<")
        val shr = Operator(">>", ">>")
        val and = Operator("&", "&")
        val or = Operator("|", "|")
    }
}