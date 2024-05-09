package ir.expression

open class Value(val value: String) : Expression() {
    override fun c(out: Appendable) {
        out.append(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Value) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}