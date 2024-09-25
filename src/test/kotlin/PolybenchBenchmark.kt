import external.Wasmtime
import external.runTimed
import java.io.File
import java.nio.file.Files
import java.util.Date
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.io.path.nameWithoutExtension
import kotlin.io.path.writeLines
import kotlin.streams.toList
import kotlin.test.Test
import kotlin.time.Duration

class PolyBenchBenchmark {
    private val _dir = Path("./samples/polybench")
    @Test
    fun benchmark(){
        val header = "name,serial time,parallel time"
        println(header)
        val results = Files.list(_dir)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }
            .map { serialFile ->
                val name = serialFile.nameWithoutExtension
                val serialTime = runTimed { Wasmtime.run(serialFile) }
                val parallelFile = WAPC.compile(serialFile)
                val parallelTime = runTimed { Wasmtime.runWithThreadsEnabled(parallelFile) }
                BenchmarkResult(name, serialTime, parallelTime)
            }
            .map(BenchmarkResult::toString)
            .map { println(it); it }
            .toList()
        val resultFile = Files.createFile(Path("./src/test/resources/polybench-${Date().time}.csv"))
        resultFile.writeLines(listOf(header) + results)
    }

    class BenchmarkResult(val name: String, val serialTime: Duration, val parallelTime: Duration) {
        override fun toString() : String {
            return "$name,$serialTime,$parallelTime"
        }
    }

}