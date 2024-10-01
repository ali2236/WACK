package analysis.ddt

import ir.statement.RangeLoop
import ir.statement.Statement

data class DependenceResult(
    val direction: Map<RangeLoop, Direction> = mapOf(),
    val conditions: List<Statement> = listOf()
) {
    companion object {
        val inconclusive = null
        val noCollision: DependenceResult = DependenceResult()
    }

    val independent: Boolean
        get() = direction.isEmpty() && conditions.isEmpty()

    fun merge(other: DependenceResult): DependenceResult {
        // TODO: merge directions properly
        return DependenceResult(direction, this.conditions + other.conditions)
    }
}