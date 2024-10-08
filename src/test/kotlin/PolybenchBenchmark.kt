import external.Wasmtime
import external.runTimed
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
    fun O0_MINI_DATASET() {
        benchmark("O0", "mini_dataset", false)
    }

    @Test
    fun O0_LARGE_DATASET() {
        benchmark("O0", "large_dataset", true)
    }

    @Test
    fun O3_LARGE_DATASET() {
        benchmark("O3", "large_dataset", true)
    }

    @Test
    fun O0_EXTRALARGE_DATASET() {
        benchmark("O0", "extralarge_dataset", true)
    }

    fun benchmark(optimization: String, datasetSize: String, writeResultsToCSV: Boolean) {
        // header
        val header = "index,name,serial time,parallel time,speedup"
        println(header)

        // run benchmark
        var i = 1
        val params = WAPC.Params(threads = 16)
        val rows = Files.list(_dir.resolve("$optimization/$datasetSize"))
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }
            .map { serialFile ->
                val name = serialFile.nameWithoutExtension
                val serialTime = runTimed { Wasmtime.run(serialFile) }
                val parallelFile = WAPC.compile(serialFile, params = params)
                val parallelTime = runTimed { Wasmtime.runWithThreadsEnabled(parallelFile) }
                BenchmarkResult(i++, name, serialTime, parallelTime)
            }.map { println(it); it } // for printing while list is not fully processed
            .toList()

        // write csv file
        if (writeResultsToCSV) {
            val filePath = Path("./src/test/resources/polybench-$optimization-$datasetSize-t${params.threads}-${Date().time}.csv")
            val resultFile = Files.createFile(filePath)
            resultFile.writeLines(listOf(header) + rows.map { it.toString() })
        }

        // avg time
        val totalSerialTime = rows.map { it.serialTime.inWholeMilliseconds }.reduce(Long::plus)
        val totalParallelTime = rows.map { it.parallelTime.inWholeMilliseconds }.reduce(Long::plus)
        val avgSpeedup = (totalSerialTime / totalParallelTime.toDouble())
        println("Avg Speedup: x${avgSpeedup}")

        // Anomalies:
        println("================ Anomalies ===================")
        //      1. no speed up
        println("No Speedup:")
        rows.filter { it.serialTime.inWholeMilliseconds > 200 }.filter { it.speedup <= 1 }.forEach { println(it) }
        println()
        //      2. speed up more than number of threads
        println("Unusual Speedup:")
        rows.filter { it.speedup >= params.threads }.forEach { println(it) }

    }

    class BenchmarkResult(val index: Int, val name: String, val serialTime: Duration, val parallelTime: Duration) {
        val speedup: Double
            get() = (serialTime.inWholeMilliseconds / parallelTime.inWholeMilliseconds.toDouble())

        override fun toString(): String {
            return "$index,$name,$serialTime,$parallelTime,x${String.format("%.2f", speedup)}"
        }
    }

}