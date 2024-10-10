package analysis.ddt

import ir.statement.SymbolLoad

// ax+c
data class Subscript(var multiplier : Int = 1, var symbol: SymbolLoad, var offset: Int = 0) {
    val valid: Boolean
        get() = multiplier != 0

    override fun toString(): String {
        val o = if(offset>0) "+$offset" else if (offset==0) "" else "$offset"
        return "(${multiplier}x$symbol$o)"
    }
}