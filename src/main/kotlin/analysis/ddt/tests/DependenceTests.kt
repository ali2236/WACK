package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

object DependenceTests : DependenceTest {


    override fun test(a1: Access, a2: Access): DependenceResult? {
        val tests = listOf(
            MemoryTest(),
            BaseTest(),
            BoundTest(),
            GCDTest(),
            RuntimeBoundTest(),
            DistanceTest(),
        )
        for (tester in tests){
            try {
                val testResult = tester.test(a1, a2)
                if(testResult != null){
                    // dependence exists
                    return testResult
                }
            } catch (e: Exception){
                // do nothing - go to the next test
            }
        }
        return null // TODO: or maybe fail
    }


}