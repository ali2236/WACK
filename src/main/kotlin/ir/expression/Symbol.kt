package ir.expression

class Symbol(symbol: String) : Value(symbol) {
    override fun dependencies(): List<Symbol> {
        return listOf(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Symbol) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}