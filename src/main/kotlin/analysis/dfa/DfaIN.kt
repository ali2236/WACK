package analysis.dfa

import ir.statement.SymbolLoad

class DfaIN {

    private val _facts = mutableMapOf<SymbolLoad, DfaFact>()
    val facts: Set<DfaFact>
        get() = _facts.values.toSet()

    fun get(symbol: SymbolLoad): DfaFact? {
        return _facts[symbol]
    }

    // return changed or not
    fun put(fact: DfaFact) : Boolean{
        val oldValue = _facts[fact.symbol]?.value
        val newValue = fact.value
        if(oldValue != newValue && oldValue != null){
            val joinedFact = DfaFact(fact.symbol, oldValue.join(newValue))
            _facts[joinedFact.symbol] = joinedFact
            val changed = oldValue != joinedFact.value
            return changed
        } else {
            _facts[fact.symbol] = fact
            return false
        }
    }

    override fun toString(): String {
        return _facts.values.map { it.toString() }.joinToString(", ")
    }
}