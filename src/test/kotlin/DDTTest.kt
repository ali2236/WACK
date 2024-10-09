import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class DDTTest : BatchWasmTester() {
    private val _dir = Path("./samples/ddt")

    @Test
    fun all(){
        batchTest(_dir)
            .map { println(it); it }
            .forEach { result ->
                assertEquals(result.serialOutput, result.parallelOutput)
        }
    }
}