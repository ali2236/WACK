package analysis.dfa

import ir.statement.Assignable

data class DfaFact(val symbol: Assignable, val value: DfaValue)