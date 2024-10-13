import kotlin.io.path.Path
import kotlin.streams.toList
import kotlin.test.Test
import kotlin.test.assertEquals

class TransformTest : BatchWasmTester() {
    private val _dir = Path("./samples/transform")

    @Test
    fun loopNormalization(){
        println(BenchmarkResult.header)
        val rows = batchTest(_dir.resolve("loop_normalization"))
            .map { println(it); it }
            .forEach { result ->
                assertEquals(result.serialOutput, result.parallelOutput)
            }
    }
}