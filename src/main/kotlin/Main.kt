import external.PolybenchOutputComparator
import external.Wasmtime
import external.runTimed
import java.io.File

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        //File("./samples/kernel_matrix_multiply.wasm"),
        //File("./samples/matrix_multiply.wasm"),
        //File("./src/test/resources/src/known_pre_allocated.wasm"),
        //File("./samples/transform/loop_normalization/loop_normalization_2.wasm"),
        File("./samples/polybench/O0/small_dataset/2mm.wasm"),
    )
    for (sample in samples) {
        val output = WAPC.compile(
            sample.toPath(),
            params = WAPC.Params(
                threads = 1,
                generateDotFiles = true,
                parallelize = true,
                parallelizeInnerLoops = false,
                normalizeLoops = true,
                enableAsserts = true,
                stripDebugNames = false,
            ),
        )
        /*var sout: String? = null
        println("running serial...")
        val s_time = runTimed { sout = Wasmtime.run(sample.toPath()) }
        println(s_time)
        println(sout.orEmpty())*/
        var pout: String? = null
        println("running parallel...")
        val p_time = runTimed { pout = Wasmtime.runWithThreadsEnabled(output) }
        println(p_time)
        println(pout.orEmpty())

        /*try {
            println("===== ${if (sout == pout) "Exact Match" else "Difference = ${PolybenchOutputComparator().compare(sout!!, pout!!)}"} =====")
        } catch (e: Exception){
            println("===== Exception =====")
        }*/
    }
}

