package analysis.ddt

import ir.statement.RangeLoop
import ir.statement.Statement

data class DependenceResult(val distance: List<Int>?, val conditions: List<Statement> = listOf()) {
    companion object {
        val inconclusive = null
        val noCollision: DependenceResult = DependenceResult(null)
    }
}