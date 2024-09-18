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
        val rangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java, true)
            .also { it.visit(function) {} }
            .result()
        for (topLevelRangeLoop in rangeLoops) {
            // find all access
            val finder = AccessFinder(topLevelRangeLoop, dfa)
            val accesses = finder.accesses()
            val subLoops = finder.subLoops()
            val accessSize = accesses.size
            val pairs = mutableListOf<AccessPair>()
            val conditions = mutableListOf<Statement>()
            // make all access pairs
            for (i in 0 until accessSize) {
                for (j in 0 until accessSize) {
                    if (i == j) continue
                    val a1 = accesses[i]
                    val a2 = accesses[j]

                    // TODO: non loop symbols can cause dependencies
                    if (subLoops.any { it.symbol == a1.symbol || it.symbol == a2.symbol }) continue

                    // determine dependency type between them
                    try {
                        val result = a1.distance(a2)

                        if (result.distance != null) {
                            val pair = AccessPair(a1, a2, result.distance)
                            pairs.add(pair)
                            conditions.addAll(result.conditions)
                        }
                    } catch (e: Exception) {
                        // TODO: this is only here to make the test fail
                        pairs.add(AccessPair(a1, a2, -1))
                        //println("Dependency probably exists")
                    }
                }
            }
            if (pairs.map { it.distance }.reduce { acc, i -> acc or i } == 0) {
                loops.add(ParallelizableLoop(topLevelRangeLoop, conditions))
            }
        }
        return loops
    }
}