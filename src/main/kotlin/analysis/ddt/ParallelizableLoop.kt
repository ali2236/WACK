package analysis.ddt

import ir.expression.Symbol
import ir.statement.RangeLoop
import ir.statement.Statement

class ParallelizableLoop(
    val loop: RangeLoop,
    val localSymbols: List<Symbol> = listOf(),
    val conditions: List<Statement> = listOf()
)