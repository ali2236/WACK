package analysis.ddt

import analysis.ddg.AccessPair
import ir.finder.SymbolMatcher
import ir.statement.RangeLoop

object SubscriptPartitioner {

    fun partition(pairs: List<AccessPair>, loops: List<RangeLoop>): List<List<AccessPair>> {
        val partitions = MutableList(pairs.size) { i -> mutableListOf(pairs[i]) }
        for (loop in loops) {
            var k: Int? = null
            partitions.forEachIndexed { j, partitionPairs ->
                val matcher = SymbolMatcher(loop.symbol)
                for (pair in partitionPairs) {
                    for (access in pair.parts) {
                        access.symbol.visit(matcher)
                    }
                }
                val loopSymbolUsedInPartitionAccessPairs = matcher.matches() > 0
                if (loopSymbolUsedInPartitionAccessPairs) {
                    if (k == null) {
                        k = j
                    } else {
                        partitions[k!!].addAll(partitionPairs)
                        partitionPairs.clear()
                    }
                }
            }
        }
        return partitions.filter { it.isNotEmpty() }
    }

}