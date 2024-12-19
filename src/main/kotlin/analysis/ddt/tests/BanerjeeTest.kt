package analysis.ddt.tests

import analysis.ddg.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import analysis.ddt.Direction
import ir.expression.Value
import ir.statement.RangeLoop

class BanerjeeTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {

        val p1 = a1.polynomial()
        val p2 = a2.polynomial()
        val diff = p1 - p2
        val dv = mutableMapOf<RangeLoop, Direction>()
        val loops = a1.scope.intersect(a2.scope)
        for (loop in loops){
            val loopSymbolSubscript = diff.getSubscript(loop.symbol)

            val L = if (loop.range.from is Value) (loop.range.from as Value).value.toInt() else 0
            val U = if (loop.range.to is Value) (loop.range.to as Value).value.toInt() else Int.MAX_VALUE
            val A = loopSymbolSubscript.multiplier
            val LB = (getNegativePart(A)) * (U - L) + A * L
            val UB = (getPositivePart(A)) * (U - L) + A * L

            if (diff.constant < LB){
                dv[loop] = Direction.Less
            } else if (diff.constant > UB){
                dv[loop] = Direction.Greater
            } else {
                dv[loop] = Direction.Equal
            }
        }

        return DependenceResult(dv)
    }

    private fun getPositivePart(a: Int): Int = if (a > 0) a else 0
    private fun getNegativePart(a: Int): Int = if (a < 0) a else 0
}