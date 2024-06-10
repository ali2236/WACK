package analysis.ddt

import analysis.dfa.Dfa
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.Loop
import ir.statement.RangeLoop

object DdtBuilder {
    fun build(function: Function) {
        val dfa = Dfa.from(function)
        // for each top level Loop

        val rangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java).result()
        for (topLevelRangeLoop in rangeLoops) {
            // find all access
            val accesses = AccessFinder().also { it.visit(topLevelRangeLoop){} }.result()
            val accessSize = accesses.size
            // make all access pairs
            for (i in 0 until accessSize){
                for (j in i until accessSize){

                    val a1 = accesses[i]
                    val a2 = accesses[j]
                    // ignore loop symbols


                    // determine dependency type between them

                    //val pair = AccessPair(a1,  a2)
                }
            }
        }

    }
}