package analysis.dfa

import ir.statement.Statement
import ir.statement.SymbolLoad

class StatementFactsFinder(dfa: Dfa) {
    private val _facts = mutableMapOf<Long, Set<DfaFact>>()

    init {
        dfa.pass { node ->
            node.statement?.id?.let {
                _facts[it] = node.OUT.facts
            }
        }
    }

    fun at(statement: Statement) : Set<DfaFact>? {
        return _facts[statement.id]
    }
}

fun Collection<DfaFact>?.whatIs(symbol: SymbolLoad): DfaValue? {
    return this?.firstOrNull { it.symbol == symbol }?.value
}