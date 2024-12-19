package analysis.ddt.tests

import analysis.ddg.Access
import analysis.ddt.*
import ir.statement.RangeLoop

class MIVTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()
        val commonLoops = a1.scope.intersect(a2.scope) // TODO: should be common and dependent loops
        val dv = mutableMapOf<RangeLoop, Direction>()
        for (loop in commonLoops) {
            val s1 = p1.getSubscript(loop.symbol)
            val s2 = p2.getSubscript(loop.symbol)
            if (!s1.valid || !s2.valid) { // if either is invalid
                dv[loop] = Direction.None
            } else if (s1.valid || s2.valid) {
                if (s1.multiplier == s2.multiplier) {
                    dv[loop] = Direction.fromInt(s1.offset - s2.offset)
                } else { // TODO: this is probably incorrect
                    dv[loop] = Direction.None
                }
            } else {
                dv[loop] = Direction.Equal
            }
        }
        return DependenceResult(dv)
    }
}