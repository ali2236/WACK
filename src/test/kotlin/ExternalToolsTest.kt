import external.Wasmtime
import external.runTimed
import kotlin.io.path.Path
import kotlin.test.Test

class ExternalToolsTest {

    @Test
    fun wasmtime(){
        Wasmtime.run(Path("./samples/simple_loop.wasm"))
    }

    @Test
    fun timer(){
        val duration = runTimed {
            Thread.sleep(500)
        }
        println(duration)
    }
}