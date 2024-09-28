package analysis.ddt

interface DependenceTest {
    fun test(a1: Access, a2: Access): DependenceResult?
}