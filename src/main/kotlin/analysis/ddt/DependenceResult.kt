package analysis.ddt

import ir.statement.RangeLoop
import ir.statement.Statement

data class DependenceResult(val direction: Map<RangeLoop, Direction> = mapOf(), val conditions: List<Statement> = listOf()) {
    companion object {
        val inconclusive = null
        val noCollision: DependenceResult = DependenceResult()
    }

    val independent : Boolean
        get() = direction.isEmpty() && conditions.isEmpty()
}