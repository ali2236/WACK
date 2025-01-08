package analysis.dfa

import ir.statement.SymbolLoad

class DfaKill {
    private val _facts = mutableSetOf<DfaFact>()
    val facts: Set<DfaFact>
        get() = _facts

    fun put(fact: DfaFact) {
        _facts.removeIf { it.symbol == fact.symbol }
        _facts.add(fact)
    }

    override fun toString(): String {
        return _facts.joinToString(", ") { it.toString() }
    }
}