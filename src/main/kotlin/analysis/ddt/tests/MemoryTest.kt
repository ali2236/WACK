package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import ir.expression.Load

class MemoryTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        if (a1.symbol is Load && a2.symbol is Load) {
            val s1 = a1.symbol
            val s2 = a2.symbol
            // check if same memory
            if (s1.memoryIndex != s2.memoryIndex) {
                return DependenceResult.noCollision
            } else {
                return DependenceResult.inconclusive
            }
        } else {
            return DependenceResult.noCollision
        }
    }
}