package analysis.ddt

abstract class DependenceTest {
    abstract fun test(a1: Access, a2: Access): DependenceResult?
    open fun exceptionResult() : DependenceResult? = DependenceResult.inconclusive
}