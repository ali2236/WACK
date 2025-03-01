package analysis.ddt

import compiler.WAPC
import analysis.ddg.AccessPair
import analysis.ddg.AccessType
import analysis.ddg.SubscriptDependenceType
import analysis.ddt.tests.*
import analysis.ddt.tests.DependenceTester
import analysis.dfa.Dfa
import ir.annotations.Skip
import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ExpressionFinder
import ir.statement.Function
import ir.statement.RangeLoop
import ir.statement.Store
import transform.allNonSkipRangeLoops

class DependenceTester(val function: Function) {

    val finder = Dfa.from(function).finder()

    fun testLoops(): List<ParallelizableLoop> {
        val loops = mutableListOf<ParallelizableLoop>()
        // for each top level Loop
        val rangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java, true)
            .also { it.visit(function) {} }
            .result()
            .filterNot { it.hasAnnotation(Skip::class.java) }
        for (topLevelRangeLoop in rangeLoops) {
            val result = testTopLevelLoop(topLevelRangeLoop)
            loops.addAll(result)
        }
        return loops
    }

    private fun testTopLevelLoop(topLevelRangeLoop: RangeLoop): List<ParallelizableLoop> {
        WAPC.stats.topLevelRangeLoops++
        val finder = AccessFinder(finder).apply { visit(topLevelRangeLoop){} }
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
                val dependenceResult = DependenceTester.dependencePossible(a1, a2)
                val dependencePossible = dependenceResult == DependenceResult.inconclusive
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
                        val directionTest = when(WAPC.params.dependenceTest){
                            WAPC.DependenceTest.banerjee -> BanerjeeTest()
                            WAPC.DependenceTest.miv -> MIVTest()
                        }

                        val result = DependenceTester.runTests(
                            pair.source,
                            pair.sink,
                            listOf(GCDTest(), directionTest)
                        )

                        result?.let {
                            dependenceResult = it.merge(dependenceResult)
                        }
                    }
                }
            }
        }
        if (pairs.isEmpty()) {
            if (accesses.any { it.symbol is Load }) {
                // no dependencies only if array kernel
                return listOf(ParallelizableLoop(topLevelRangeLoop))
            }
        } else if (dependenceResult != null) {
            val noCollision = dependenceResult == DependenceResult.noCollision
            val topLevelHasNoDependence =
                dependenceResult!!.direction[topLevelRangeLoop] == Direction.Equal
            if (noCollision || topLevelHasNoDependence) {
                return listOf(ParallelizableLoop(topLevelRangeLoop))
            } else if (WAPC.params.parallelizeInnerLoops) {
                // inner-loops
                var parallelizableLoops = dependenceResult!!.direction.filter { it.value == Direction.Equal }
                parallelizableLoops =
                    parallelizableLoops.filter { it.key.range.from == Value.zero } // should not be dependent on outer loop symbol
                // select the ones that are not sub-loops of other parallelizable loops
                parallelizableLoops =
                    parallelizableLoops.filter { (pl) -> !parallelizableLoops.any { (loop) -> pl.childOf(loop) } }
                return parallelizableLoops.map { ParallelizableLoop(it.key) }
            }
        } else {
            return listOf(ParallelizableLoop(topLevelRangeLoop))
        }
        return listOf()
    }
}