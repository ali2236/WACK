package analysis.ddt

import analysis.dfa.Dfa
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.RangeLoop
import ir.statement.Statement
import java.lang.Exception

object DependenceTester {
    fun testLoops(function: Function): List<ParallelizableLoop> {
        val dfa = Dfa.from(function)
        val loops = mutableListOf<ParallelizableLoop>()
        // for each top level Loop
        val rangeLoops =
            BreadthFirstExpressionFinder(RangeLoop::class.java, true).also { it.visit(function) {} }.result()
        for (topLevelRangeLoop in rangeLoops) {
            // find all access
            val finder = AccessFinder(topLevelRangeLoop, dfa)
            val subLoops = finder.subLoops()
            // don't include access to loop symbols
            val accesses = finder.accesses().filter { access ->
                    !subLoops.any { loop ->
                        loop.symbol == access.symbol
                    }
                }
            val accessSize = accesses.size
            val pairs = mutableListOf<AccessPair>()
            // make all access pairs
            for (i in 0 until accessSize) {
                for (j in 0 until accessSize) {
                    if (i == j) continue
                    val a1 = accesses[i]
                    val a2 = accesses[j]

                    // TODO: non loop symbols can cause dependencies

                    // determine dependency type between them
                    try {
                        val result = a1.distance(a2)

                        if (result.distance != null || result.conditions.isNotEmpty()) {
                            val pair = AccessPair(a1, a2, result)
                            pairs.add(pair)
                        }
                    } catch (e: Exception) {
                        // TODO: this is only here to make the test fail
                        pairs.add(AccessPair(a1, a2, DistanceResult(-1)))
                        //println("Dependency probably exists")
                    }
                }
            }
            var distance = 0
            var conditions = listOf<Statement>()
            for(pair in pairs){
                distance = distance or (pair.distanceInfo.distance ?: 0)
                conditions = conditions + pair.distanceInfo.conditions
            }
            if (distance == 0) {
                loops.add(ParallelizableLoop(topLevelRangeLoop, conditions))
            }
        }
        return loops
    }
}