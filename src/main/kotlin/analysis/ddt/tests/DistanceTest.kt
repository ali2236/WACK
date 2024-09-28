package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import ir.expression.Value

class DistanceTest : DependenceTest {
    override fun test(a1: Access, a2: Access): DependenceResult {
        // TODO: distance is only relevant if they are in the same address
        val source = a1.polynomial()
        val sink = a2.polynomial()
        val distance = sink - source

        return DependenceResult(listOf(5, 4))
    }
}