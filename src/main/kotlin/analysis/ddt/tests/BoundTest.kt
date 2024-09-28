package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

class BoundTest : DependenceTest {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        try {
            val b1 = a1.bounds()
            val b2 = a2.bounds()
            if (!b1.intersect(b2)) {
                return DependenceResult.noCollision
            } else {
                return DependenceResult.inconclusive
            }
        } catch (e: Exception) {
            return DependenceResult.inconclusive
        }
    }
}