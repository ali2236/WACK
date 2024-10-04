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
    fun benchmark(){
        // header
        val header = "name,serial time,parallel time,speedup"
        println(header)

        // run benchmark
        val rows = Files.list(_dir)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }
            .map { serialFile ->
                val name = serialFile.nameWithoutExtension
                val serialTime = runTimed { Wasmtime.run(serialFile) }
                val parallelFile = WAPC.compile(serialFile)
                val parallelTime = runTimed { Wasmtime.runWithThreadsEnabled(parallelFile) }
                BenchmarkResult(name, serialTime, parallelTime)
            }.map { println(it); it } // for printing while list is not fully processed
            .toList()

        // write csv file
        val resultFile = Files.createFile(Path("./src/test/resources/polybench-${Date().time}.csv"))
        resultFile.writeLines(listOf(header) + rows.map { it.toString() })

        // avg time
        val totalSerialTime = rows.map { it.serialTime.inWholeMilliseconds }.reduce(Long::plus)
        val totalParallelTime = rows.map { it.parallelTime.inWholeMilliseconds }.reduce(Long::plus)
        val avgSpeedup = (totalSerialTime / totalParallelTime.toDouble())
        println("Avg Speedup: x${avgSpeedup}")

        // Anomalies:
        println("================ Anomalies ===================")
        //      1. no speed up
        println("No Speedup:")
        rows.filter { it.speedup <= 1 }.forEach { println(it) }
        println()
        //      2. speed up more than number of threads
        println("Unusual Speedup:")
        rows.filter { it.speedup >= 8 }.forEach { println(it) }

    }

    class BenchmarkResult(val name: String, val serialTime: Duration, val parallelTime: Duration) {
        val speedup : Double
            get() = (serialTime.inWholeMilliseconds / parallelTime.inWholeMilliseconds.toDouble())
        override fun toString() : String {
            return "$name,$serialTime,$parallelTime,x${String.format("%.2f", speedup)}"
        }
    }

}