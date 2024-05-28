package analysis.dfa

import ir.statement.Assignable

data class DfaFact(val symbol: Assignable, val value: DfaValue){


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

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}