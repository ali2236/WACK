import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class DDTTest : BatchWasmTester() {
    private val _dir = Path("./samples/ddt")

    @Test
    fun all(){
        gcd()
    }

    @Test
    fun gcd(){
        // can also test [reduction] & [loop normalization: loops that don't start from 0]
        batchTest(_dir.resolve("gcd"))
            .map { println(it); it }
            .forEach { result ->
                assertEquals(result.serialOutput, result.parallelOutput)
            }
    }
}