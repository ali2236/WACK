import external.Wasmtime
import external.runTimed
import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class ExternalToolsTest {

    @Test
    fun wasmtime(){
        val output = Wasmtime.run(Path("./samples/print.wasm"))
        assertEquals("hi", output)
    }

    @Test
    fun timer(){
        val duration = runTimed {
            Thread.sleep(500)
        }
        println(duration)
    }
}