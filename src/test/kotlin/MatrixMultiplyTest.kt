import external.Wasmtime
import external.runTimed
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.test.Test

class MatrixMultiplyTest {

    @Test
    fun runAll(){
        val allWasmFilePaths = Files.list(Path("./src/test/resources/src"))
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }

        for (path in allWasmFilePaths){
            val inputRunTime = runTimed { Wasmtime.run(path) }
            val output = WAPC.compile(path)
            val outputRunTime = runTimed { Wasmtime.runWithThreadsEnabled(output) }
            println("${path.fileName}: before=${inputRunTime}; after=${outputRunTime}")
        }
    }

}