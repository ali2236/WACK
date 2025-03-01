package analysis.ddt.tests

import analysis.ddg.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import analysis.ddt.Direction

class ZIVTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()
        val diff = p2 - p1
        if(diff.constant == 0 && diff.subscripts.all { it.multiplier == 0 }){
            // dependent
            // add all direction vectors for all common loops
            val commonLoops = a1.scope.intersect(a2.scope)
            val directionVector = commonLoops.associateWith { Direction.Any }
            return DependenceResult(directionVector)
        } else {
            // independent
            return DependenceResult.noCollision
        }
    }
}