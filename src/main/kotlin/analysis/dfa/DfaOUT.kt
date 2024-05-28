package analysis.dfa

import ir.expression.Symbol
import ir.statement.Assignable
import wasm.Index
import wasm.WasmScope
import wasm.WasmValueType

class DfaOUT {

    private val _facts = mutableMapOf<Assignable, DfaFact>()
    val facts: Set<DfaFact>
        get() = _facts.values.toSet()

    fun get(symbol: Assignable): DfaFact {
        return _facts.getOrPut(symbol) { DfaFact(symbol, DfaValue.Undeclared()) }
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