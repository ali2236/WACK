package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

class GCDTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()

        val multipliers = p1.multipliers() + p2.multipliers()
        val gcd = gcd(multipliers)
        val rem = p1.constant - p2.constant
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