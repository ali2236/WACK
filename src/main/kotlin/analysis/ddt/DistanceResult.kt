package analysis.ddt

import ir.statement.Statement

data class DistanceResult(val distance: Int?, val conditions: List<Statement> = listOf()) {
    companion object {
        val collision: DistanceResult = DistanceResult(1, listOf())
        val noCollision: DistanceResult = DistanceResult(null, listOf())
    }
}