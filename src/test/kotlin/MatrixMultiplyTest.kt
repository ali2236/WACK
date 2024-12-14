import compiler.WAPC
import external.Make
import external.Wasmtime
import external.runTimed
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.io.path.name
import kotlin.test.Test
import kotlin.test.assertEquals

class MatrixMultiplyTest {
    private val _dir = Path("./src/test/resources/src")

    init {
        Make(_dir).run()
    }

    @Test
    fun runAll() {
        val allWasmFilePaths = Files.list(_dir)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }

        for (path in allWasmFilePaths) {
            println("Testing ${path.fileName}")
            var inputOutput: String = ""
            var outputOutput: String = ""
            val output = WAPC.compile(path)
            val outputRunTime = runTimed { outputOutput = Wasmtime.runWithThreadsEnabled(output) }
            println("Auto-Parallel Version - ${path.name}")
            println(outputRunTime)
            print(outputOutput)
            val inputRunTime = runTimed { inputOutput = Wasmtime.run(path) }
            println("Serial Version - ${path.name}")
            println(inputRunTime)
            print(inputOutput)
            // assert output unchanged
            assertEquals(inputOutput, outputOutput, "Different output is produced for ${path.fileName}")
        }
    }

}