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

    fun batchTest(dir: Path, params: WAPC.Params = WAPC.Params()) : Stream<BenchmarkResult> {
        var i = 1
        return Files.list(dir)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }
            .map {serialFile ->
                val name = serialFile.nameWithoutExtension
                val serialTime = runTimed { Wasmtime.run(serialFile) }
                val parallelFile = WAPC.compile(serialFile, params = params)
                val parallelTime = runTimed { Wasmtime.runWithThreadsEnabled(parallelFile) }
                BenchmarkResult(i++, name, serialTime, parallelTime)
            }
    }

    fun writeToCSVFile(name: String, data: List<BenchmarkResult>){
        val filePath = Path("./src/test/resources/$name.csv")
        val resultFile = Files.createFile(filePath)
        resultFile.writeLines(listOf(BenchmarkResult.header) + data.map(BenchmarkResult::toString))
    }

    class BenchmarkResult(val index: Int, val name: String, val serialTime: Duration, val parallelTime: Duration) {
        val speedup: Double
            get() = (serialTime.inWholeMilliseconds / parallelTime.inWholeMilliseconds.toDouble())

        override fun toString(): String {
            return "$index,$name,$serialTime,$parallelTime,x${String.format("%.2f", speedup)}"
        }

        companion object {
            val header = "index,name,serial time,parallel time,speedup"
        }
    }
}