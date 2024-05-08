package ir.expression

open class Value(val value: String) : Expression() {
    override fun c(out: Appendable) {
        out.append(value)
    }
}