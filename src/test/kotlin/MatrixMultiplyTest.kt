import external.Make
import external.Wasmtime
import external.runTimed
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
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

    fun runForFile(path: Path){
        println("Testing ${path.fileName}")
        var inputOutput : String = ""
        var outputOutput : String = ""
        val output = WAPC.compile(path)
        val outputRunTime = runTimed { outputOutput = Wasmtime.runWithThreadsEnabled(output) }
        println("Auto-Parallel Version - ${path.name}")
        println(outputRunTime)
        print(outputOutput)
        val inputRunTime = runTimed { inputOutput = Wasmtime.run(path) }
        println("Serial Version - ${path.name}")
        println(inputRunTime)
        print(inputOutput)
        // TODO: assert output unchanged
        assertEquals(inputOutput, outputOutput, "Different output is produced for ${path.fileName}")
        //println("${path.fileName}: before=${inputRunTime}; after=${outputRunTime}")
        // TODO: assert better performance
    }

    @Test
    fun runAll(){
        val allWasmFilePaths = Files.list(_dir)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }

        for (path in allWasmFilePaths){
            runForFile(path)
        }
    }

    @Test
    fun known_stack_allocated(){
        runForFile(_dir.resolve("known_stack_allocated.wasm"))
    }

}