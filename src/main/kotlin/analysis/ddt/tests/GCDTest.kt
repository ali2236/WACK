package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import kotlin.math.abs

class GCDTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()

        val b1 = p1.base()
        val b2 = p2.base()

        val s1 = p1.subscripts.filter { it.symbol != b1 }
        val s2 = p2.subscripts.filter { it.symbol != b2 }

        val m1 = s1.map { it.multiplier }
        val m2 = s2.map { it.multiplier }
        val multipliers = m1 + m2
        val gcd = gcd(multipliers)

        val c1 = p1.subscripts.sumOf { it.offset } - p1.constant
        val c2 = p2.subscripts.sumOf { it.offset } - p2.constant
        val rem = abs(c1 - c2)

        if(rem % gcd == 0){ // depintine equasion has a solution
            // dependent
            return DependenceResult.inconclusive
        } else {
            // independent
            return DependenceResult.noCollision
        }
    }

    companion object {
        fun gcd(a: Int, b: Int): Int {
            return if (b == 0) a else gcd(b, a % b)
        }

        fun gcd(numbers: List<Int>): Int {
            return numbers.reduce { acc, number -> gcd(acc, number) }
        }
    }

}