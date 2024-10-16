import external.Wasmtime
import external.runTimed
import java.io.File

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        //File("./samples/kernel_matrix_multiply.wasm"),
        //File("./samples/matrix_multiply.wasm"),
        File("./src/test/resources/src/known_pre_allocated.wasm")
        //File("./samples/transform/loop_normalization/loop_normalization.wasm"),
        //File("./samples/polybench/O0/large_dataset/3mm.wasm"),
    )
    for (sample in samples) {
        val output = WAPC.compile(
            sample.toPath(),
            params = WAPC.Params(
                threads = 8,
                generateDotFiles = true,
                parallelize = true,
                parallelizeInnerLoops = false,
                normalizeLoops = false,
            ),
        )
        var sout: String? = null
        var pout: String? = null
        val s_time = runTimed { sout = Wasmtime.run(sample.toPath()) }
        println("running serial...")
        println(s_time)
        println(sout.orEmpty())
        val p_time = runTimed { pout = Wasmtime.runWithThreadsEnabled(output) }
        println("running parallel...")
        println(p_time)
        println(pout.orEmpty())
    }
}

