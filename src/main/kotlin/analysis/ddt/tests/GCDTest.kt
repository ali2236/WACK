package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

class GCDTest : DependenceTest {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()

        val multipliers = p1.multipliers() + p2.multipliers()
        val gcd = gcd(multipliers.map { it.value.toInt() })
        val rem = p1.getOffset().value.toInt() - p2.getOffset().value.toInt()
        if(rem % gcd == 0){
            // independent
            return DependenceResult.noCollision
        } else {
            // dependent
            return DependenceResult.inconclusive
        }
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    private fun gcd(numbers: List<Int>): Int {
        return numbers.reduce { acc, number -> gcd(acc, number) }
    }

}