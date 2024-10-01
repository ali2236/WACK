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

    fun merge(other: DependenceResult?): DependenceResult {
        if(other == null) return this
        val allLoops = direction.keys + other.direction.keys
        val dirs = mutableMapOf<RangeLoop, Direction>()
        for (loop in allLoops){
            val d1 = other.direction[loop]
            val d2 = other.direction[loop]
            if (d1 == null && d2 != null){
                dirs[loop] = d2
            } else if(d2 == null && d1 != null){
                dirs[loop] = d1
            } else if (d1 != null && d2 != null){
                dirs[loop] = d1.product(d2)
            }
        }
        return DependenceResult(direction, this.conditions + other.conditions)
    }
}