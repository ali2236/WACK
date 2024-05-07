package ir.expression


class Assignment(val symbol: String, val value: Expression) : Expression() {
    override fun c(out: Appendable) {
        out.append(symbol)
        out.append(" = ")
        value.c(out)
        out.append(';')
        out.append("\n")
    }
}