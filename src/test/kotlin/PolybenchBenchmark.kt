import java.util.*
import kotlin.io.path.Path
import kotlin.streams.toList
import kotlin.test.Test

class PolyBenchBenchmark : BatchWasmTester() {
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
        println(BenchmarkResult.header)

        // run benchmark
        val params = WAPC.Params(threads = 16)
        val rows = batchTest(_dir.resolve("$optimization/$datasetSize"), params)
            .map { println(it); it }
            .toList()

        // write csv file
        if(writeResultsToCSV) {
            writeToCSVFile("polybench-$optimization-$datasetSize-t${params.threads}-${Date().time}", rows)
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



}