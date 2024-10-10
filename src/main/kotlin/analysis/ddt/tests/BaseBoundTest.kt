package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

class BaseBoundTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val b1 = a1.bounds()
        val b2 = a2.bounds()
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()
        val d = p1 - p2
        if(d.symbols().isEmpty()){
            val offset = d.getOffset()

        }
        return DependenceResult()
    }
}