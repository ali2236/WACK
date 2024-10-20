import external.Wasmtime
import external.runTimed
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.io.path.nameWithoutExtension
import kotlin.io.path.writeLines
import kotlin.time.Duration

abstract class BatchWasmTester {

    fun batchTest(dir: Path, params: WAPC.Params = WAPC.Params(), skip: Set<String> = setOf()): Stream<BenchmarkResult> {
        var i = 1
        return Files.list(dir)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }
            .filter { !skip.contains(it.nameWithoutExtension) }
            .map { serialFile ->
                val name = serialFile.nameWithoutExtension
                lateinit var serialOutput: String
                lateinit var parallelOutput: String
                val serialTime = runTimed { serialOutput = Wasmtime.run(serialFile) }
                val parallelFile = WAPC.compile(serialFile, params = params)
                val parallelTime = runTimed { parallelOutput = Wasmtime.runWithThreadsEnabled(parallelFile) }
                BenchmarkResult(i++, name, serialTime, parallelTime, serialOutput, parallelOutput)
            }
    }

    fun writeToCSVFile(name: String, data: List<BenchmarkResult>) {
        val filePath = Path("./src/test/resources/$name.csv")
        val resultFile = Files.createFile(filePath)
        resultFile.writeLines(listOf(BenchmarkResult.header) + data.map(BenchmarkResult::toString))
    }

    class BenchmarkResult(
        val index: Int,
        val name: String,
        val serialTime: Duration,
        val parallelTime: Duration,
        val serialOutput: String,
        val parallelOutput: String,
    ) {
        val speedup: Double
            get() = (serialTime.inWholeMilliseconds / parallelTime.inWholeMilliseconds.toDouble())

        override fun toString(): String {
            return "$index,$name,$serialTime,$parallelTime,${String.format("%.2f", speedup)}"
        }

        companion object {
            val header = "index,name,serial time,parallel time,speedup"
        }
    }
}