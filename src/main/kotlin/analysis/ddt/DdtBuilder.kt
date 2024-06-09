package analysis.ddt

import ir.finder.BreadthFirstExpressionFinder
import ir.statement.ConditionLoop
import ir.statement.RangeLoop

object DdtBuilder {
    fun build() {
        // for each top level Loop
        val rangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java).result()
        for (loop in rangeLoops) {
            // find all access
            val accesses = AccessFinder().also { it.visit(loop){} }.result()
            val accessSize = accesses.size
            // make all access pairs
            for (i in 0 until accessSize){
                for (j in i until accessSize){
                    val a1 = accesses[i]
                    val a2 = accesses[j]
                    // determine dependency type between them

                    val pair = AccessPair(a1,  a2)
                }
            }
        }

    }
}