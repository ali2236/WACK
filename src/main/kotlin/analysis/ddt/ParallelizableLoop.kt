package analysis.ddt

import ir.statement.RangeLoop
import ir.statement.Statement

class ParallelizableLoop(val loop: RangeLoop, val conditions: List<Statement> = listOf())