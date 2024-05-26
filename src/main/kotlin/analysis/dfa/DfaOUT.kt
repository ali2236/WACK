package analysis.dfa

import ir.statement.Assignable

class DfaOUT {

    private val _facts = mutableMapOf<Assignable, DfaFact>()
    val facts : Set<DfaFact>
        get() = _facts.values.toSet()

    fun get(symbol: Assignable) : DfaFact{
        return _facts.getOrDefault(symbol, DfaFact(symbol, DfaValue.UpperBound()))
    }

    fun put(fact: DfaFact){
        _facts[fact.symbol] = fact
    }
}