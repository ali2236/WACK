package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

class BaseTest : DependenceTest {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val b1 = a1.polynomial().base()
        val b2 = a2.polynomial().base()
        if(b1 != b2){
            return DependenceResult.noCollision
        }
        return DependenceResult.inconclusive
    }
}