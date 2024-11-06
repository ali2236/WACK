package analysis.ddg

import ir.finder.SymbolMatcher
import ir.statement.SymbolLoad

data class AccessPair(val source : Access, val sink : Access) {

    val parts: List<Access>
        get() = listOf(source, sink)

    // MIV or SIV
    fun findType() : SubscriptDependenceType {
        val symbols = mutableSetOf<SymbolLoad>()
        for(part in parts){
            for (loop in part.scope.loops){
                val matcher = SymbolMatcher(loop.symbol)
                part.symbol.visit(matcher)
                if(matcher.matches() > 0){
                    symbols.add(loop.symbol)
                }
            }
        }
      return SubscriptDependenceType.fromSymbolsUsed(symbols.size)
    }

    fun dependenceType() {

    }
}