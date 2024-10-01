package analysis.ddt.tests

import analysis.ddt.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest

object DependenceTester {

/*
    fun test(a1: Access, a2: Access): DependenceResult? {
        val tests = listOf(
            GCDTest(),
            BoundTest(),
            //RuntimeBoundTest(),
            //DistanceTest(),
        )
        return runTests(a1, a2, tests)
    }
*/

    fun dependencePossible(a1: Access, a2: Access): DependenceResult?{
        val tests = listOf(
            MemoryTest(),
            BaseTest(),
        )
        return runTests(a1, a2, tests)
    }

    fun runTests(a1: Access, a2: Access, tests: List<DependenceTest>) : DependenceResult? {
        for (tester in tests){
            val testResult = try {
                tester.test(a1, a2)
            } catch (e: Exception){
                tester.exceptionResult()
            }
            if(testResult != null){
                // dependence exists
                return testResult
            }
        }
        return DependenceResult.inconclusive
    }

}