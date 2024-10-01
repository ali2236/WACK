package analysis.ddt.tests

import analysis.ddt.*
import ir.statement.RangeLoop

class MIVTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()
        val commonLoops = a1.scope.intersect(a2.scope)
        val dv = mutableMapOf<RangeLoop, Direction>()
        for (loop in commonLoops){
            val s1 = p1.getSubscript(loop.symbol)
            val s2 = p2.getSubscript(loop.symbol)
            if(!s1.valid && !s2.valid){
                dv[loop] = Direction.Any
            } else {
                dv[loop] = Direction.fromInt(s1.offset - s2.offset)
            }
        }
        return DependenceResult(dv)
    }
}