package ir.expression


class Assignment(val symbol: Symbol, val value: Expression) : Expression() {
    override fun c(out: Appendable) {
        symbol.c(out)
        out.append(" = ")
        value.c(out)
        out.append(';')
        out.append("\n")
    }
}