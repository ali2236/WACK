package analysis.ddt

import WAPC
import analysis.ddt.tests.*
import analysis.ddt.tests.DependenceTester
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
            BreadthFirstExpressionFinder(RangeLoop::class.java, true)
                .also { it.visit(function) {} }
                .result()
        for (topLevelRangeLoop in rangeLoops) {
            val result = testTopLevelLoop(topLevelRangeLoop)
            loops.addAll(result)
        }
        return loops
    }

    private fun testTopLevelLoop(topLevelRangeLoop: RangeLoop): List<ParallelizableLoop> {
        val finder = AccessFinder(topLevelRangeLoop, dfa)
        if (finder.functionCalls().isNotEmpty()) {
            // has function calls
            // no parallel loops
            return listOf()
        }
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
                if (dependencePossible) {
                    pairs.add(AccessPair(a1, a2))
                }
            }
        }
        val partitionedPairs = SubscriptPartitioner.partition(pairs, subLoops)
        var dependenceResult: DependenceResult? = null
        for (partition in partitionedPairs) {
            for (pair in partition) {
                when (pair.findType()) {
                    SubscriptDependenceType.ZIV -> {
                        val result = DependenceTester.runTests(
                            pair.source,
                            pair.sink,
                            listOf(ZIVTest())
                        )
                        if (result?.independent == true) {
                            break // partition is independent
                        } else {
                            result?.let {
                                dependenceResult = it.merge(dependenceResult)
                            }
                        }
                    }

                    else -> {
                        // gcd test
                        // banerjee test
                        // range test
                        val result = DependenceTester.runTests(
                            pair.source,
                            pair.sink,
                            listOf(GCDTest(), MIVTest())
                        )

                        result?.let {
                            dependenceResult = it.merge(dependenceResult)
                        }
                    }
                }
            }
        }
        // TODO: runtime dependence test
        // TODO: use dependence result to return parallel loop
        if (pairs.isEmpty()) {
            // no dependencies
            return listOf(ParallelizableLoop(topLevelRangeLoop))
        } else if (dependenceResult != null) {
            val noCollision = dependenceResult == DependenceResult.noCollision
            val topLevelHasNoDependence =
                dependenceResult!!.direction[topLevelRangeLoop] == Direction.Equal
            if (noCollision || topLevelHasNoDependence) {
                return listOf(ParallelizableLoop(topLevelRangeLoop))
            } else if (WAPC.params!!.parallelizeInnerLoops) {
                // inner-loops
                var parallelizableLoops = dependenceResult!!.direction.filter { it.value == Direction.Equal }
                // select the ones that are not sub-loops of other parallelizable loops
                parallelizableLoops = parallelizableLoops.filter { (pl, d) -> !parallelizableLoops.any { (loop, d) -> pl.childOf(loop) } }
                return parallelizableLoops.map { ParallelizableLoop(it.key) }
            }
        }
        return listOf()
    }
}