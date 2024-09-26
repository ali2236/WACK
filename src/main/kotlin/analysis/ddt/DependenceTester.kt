package analysis.ddt

import analysis.dfa.Dfa
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.RangeLoop
import ir.statement.Statement
import java.util.Stack

class DependenceTester(val function: Function) {

    val dfa = Dfa.from(function)

    fun testLoops(): List<ParallelizableLoop> {
        val loops = mutableListOf<ParallelizableLoop>()
        // for each top level Loop
        val rangeLoops =
            BreadthFirstExpressionFinder(RangeLoop::class.java, true).also { it.visit(function) {} }.result()
        for (topLevelRangeLoop in rangeLoops) {
            val result = testLoop(topLevelRangeLoop)
            loops.addAll(result)
        }
        return loops
    }

    // return most outer parallizable loop
    // if the outer loop is not parallizable should return a list of nested loops that are
    // if nothing is parallalizable return an empty list
    private fun testLoop(loop: RangeLoop, parents: List<RangeLoop> = listOf()): List<ParallelizableLoop> {
        // check children
        val subLoops = BreadthFirstExpressionFinder(RangeLoop::class.java).also { loop.visit(it) }.result()
        val subParallelLoops = subLoops.map { testLoop(it, parents + listOf(loop)) }.flatten()
        val everyChildIsParallel = subLoops.all { sub -> subParallelLoops.any { par -> par.loop == sub } }

        // if self & all_children -> self
        // if !self -> children
        if (everyChildIsParallel) {
            // check self
            val parallelLoop = checkIfLoopIsParallelizable(loop, parents)
            if (parallelLoop != null) {
                // carry children conditions
                val childrenConditions = subParallelLoops.map { it.conditions }.flatten()
                return listOf(ParallelizableLoop(parallelLoop.loop, parallelLoop.conditions + childrenConditions))
            }
        } else {
            return subParallelLoops
        }
        return listOf()
    }

    private fun checkIfLoopIsParallelizable(loop: RangeLoop, parents: List<RangeLoop> = listOf()): ParallelizableLoop? {
        val scope = Stack<RangeLoop>()
        parents.forEach(scope::push)
        scope.push(loop)
        val accesses = BreadthOnlyAccessFinder(scope, dfa.finder()).accesses()
        val pairs = mutableListOf<AccessPair>()
        for (i in accesses.indices) {
            for (j in accesses.indices) {
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
        for (pair in pairs) {
            distance = distance or (pair.distanceInfo.distance ?: 0)
            conditions = conditions + pair.distanceInfo.conditions
        }
        if (distance == 0) {
            return ParallelizableLoop(loop, conditions)
        }
        return null
    }
}