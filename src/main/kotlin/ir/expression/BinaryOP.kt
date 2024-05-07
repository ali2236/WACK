package ir.expression

class BinaryOP(val operator: String, val first: Expression, val second: Expression) : Expression() {
    override fun c(out: Appendable) {
        if (first is BinaryOP) out.append("(")
        first.c(out)
        if (first is BinaryOP) out.append(")")

        out.append(operator)
        if (second is BinaryOP) out.append("(")
        second.c(out)
        if (second is BinaryOP) out.append(")")
    }
}