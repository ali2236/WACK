package analysis.ddt

import analysis.ddt.tests.DependenceTester
import analysis.ddt.tests.GCDTest
import analysis.ddt.tests.MIVTest
import analysis.ddt.tests.ZIVTest
import analysis.dfa.Dfa
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.RangeLoop

class DependenceTester(val function: Function) {

    val dfa = Dfa.from(function)

    fun testLoops(): List<ParallelizableLoop> {
        val loops = mutableListOf<ParallelizableLoop>()
        // for each top level Loop
        val rangeLoops =
            BreadthFirstExpressionFinder(RangeLoop::class.java, true).also { it.visit(function) {} }.result()
        for (topLevelRangeLoop in rangeLoops) {
            val result = testTopLevelLoop(topLevelRangeLoop)
            loops.addAll(result)
        }
        return loops
    }

    private fun testTopLevelLoop(topLevelRangeLoop: RangeLoop) : List<ParallelizableLoop> {
        val finder = AccessFinder(topLevelRangeLoop, dfa)
        val subLoops = finder.subLoops()
        val accesses = finder.accesses().filter { access ->
            // don't include access to loop symbols
            !subLoops.any { loop ->
                loop.symbol == access.symbol
            }
        }
        val pairs = mutableListOf<AccessPair>()
        for (i in accesses.indices) {
            for (j in accesses.indices) {
                if (i == j) continue
                val a1 = accesses[i]
                val a2 = accesses[j]
                if (a1.accessType == a2.accessType && a2.accessType == AccessType.Read) continue
                val dependencePossible = DependenceTester.dependencePossible(a1, a2) == DependenceResult.inconclusive
                if(dependencePossible){
                    pairs.add(AccessPair(a1, a2))
                }
            }
        }
        val partitionedPairs = SubscriptPartitioner.partition(pairs, subLoops)
        for(partition in partitionedPairs){
            for (pair in partition){
                when(pair.findType()){
                    SubscriptDependenceType.ZIV -> {
                       val result = DependenceTester.runTests(
                           pair.source,
                           pair.sink,
                           listOf(ZIVTest())
                       )
                        if(result?.independent == true){
                            break // partition is independent
                        }
                    }
                    else -> {
                        // gcd test
                        // bunjree test
                        // range test
                        val result = DependenceTester.runTests(
                            pair.source,
                            pair.sink,
                            listOf(GCDTest(), MIVTest())
                            )
                        println(result)
                    }
                }
            }
        }
        return listOf(ParallelizableLoop(topLevelRangeLoop))
    }
}