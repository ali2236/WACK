package analysis.dfa

import ir.statement.SymbolLoad

class DfaFact(val symbol: SymbolLoad, val value: DfaValue) {

    override fun toString(): String {
        return "($symbol, $value)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DfaFact) return false

        if (symbol != other.symbol) return false
        if (value != other.value) return false

        return true
    }

    fun clone(): DfaFact {
        return DfaFact(
            symbol.clone() as SymbolLoad,
            when (value) {
                is DfaValue.Expr -> DfaValue.Expr(value.value.clone())
                else -> value
            },
        )
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}