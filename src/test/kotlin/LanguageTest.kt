import kotlin.io.path.Path
import kotlin.streams.toList
import kotlin.test.Test

class LanguageTest : BatchWasmTester() {
    private val _dir = Path("./samples/languages")

    @Test
    fun c() {
        println(BenchmarkResult.header)
        val rows = batchTest(_dir.resolve("c"))
            .map { println(it); it }
            .toList()
    }

    @Test
    fun golang() {
        println(BenchmarkResult.header)
        val rows = batchTest(_dir.resolve("go"))
            .map { println(it); it }
            .toList()
    }
}