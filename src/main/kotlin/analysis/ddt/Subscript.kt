package analysis.ddt

import ir.statement.SymbolLoad

// ax+c
data class Subscript(val multiplier : Int, val symbol: SymbolLoad, val offset: Int) {
    val valid: Boolean
        get() = multiplier != 0
}