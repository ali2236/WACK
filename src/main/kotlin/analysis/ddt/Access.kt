package analysis.ddt

import ir.statement.SymbolLoad

data class Access(
    val symbol: SymbolLoad,
    val accessType : AccessType,
)