package analysis.duc

import analysis.dfa.Dfa
import ir.statement.Function
import ir.statement.SymbolLoad

class DefUseChain(
    private val symbols: MutableList<SymbolUseDefChain> = mutableListOf()
) {

    fun getSymbol(symbol: SymbolLoad): SymbolUseDefChain {
        var sudc = symbols.firstOrNull { it.symbol == symbol }
        if (sudc == null) {
            sudc = SymbolUseDefChain(symbol)
            symbols.add(sudc)
        }
        return sudc
    }

    companion object {
        fun from(function: Function) : DefUseChain {
            return UseDefAnalyzer.analyze(function, Dfa.from(function))
        }
    }

}