package analysis.ddt

import analysis.dfa.Dfa
import ir.expression.Symbol
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.RangeLoop

object DdtBuilder {
    fun build(function: Function) {
        val dfa = Dfa.from(function)
        // for each top level Loop
        val rangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java, true)
            .also { it.visit(function){} }
            .result()
        for (topLevelRangeLoop in rangeLoops) {
            // find all access
            val finder = AccessFinder(topLevelRangeLoop, dfa)
            val accesses = finder.accesses()
            val subLoops = finder.subLoops()
            val accessSize = accesses.size
            // make all access pairs
            for (i in 0 until accessSize){
                for (j in 0 until accessSize){
                    val a1 = accesses[i]
                    val a2 = accesses[j]

                    // TODO: non loop symbols can cause dependencies
                    if(subLoops.any { it.symbol == a1.symbol || it.symbol == a2.symbol }) continue

                    // determine dependency type between them
                    val distance = a1.distance(a2)

                    //val pair = AccessPair(a1,  a2)
                }
            }
        }
    }
}