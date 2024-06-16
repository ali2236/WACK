package analysis.dfa

import ir.statement.SymbolLoad

class DfaOUT {

    private val _facts = mutableMapOf<SymbolLoad, DfaFact>()
    val facts: Set<DfaFact>
        get() = _facts.values.toSet()

    fun get(symbol: SymbolLoad): DfaFact {
        return _facts.getOrPut(symbol) { DfaFact(symbol, DfaValue.Unknown()) }
    }

    // return changed or not
    fun put(fact: DfaFact) : Boolean{
        val oldValue = _facts[fact.symbol]?.value
        val newValue = fact.value
        val changed = oldValue != newValue
        _facts[fact.symbol] = fact
        return changed
    }

    override fun toString(): String {
        return _facts.values.map { it.toString() }.joinToString(", ")
    }
}