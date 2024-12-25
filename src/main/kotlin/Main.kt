import compiler.WACK
import compiler.WAPC
import external.PolybenchOutputComparator
import external.Wasmtime
import external.runTimed
import java.io.File

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        //File("./samples/languages/3mm.go.wasm"),
        //File("./samples/matrix_multiply.wasm"),
        //File("./src/test/resources/src/known_pre_allocated.wasm"),
        //File("./samples/transform/loop_normalization/loop_normalization_2.wasm"),
        File("./samples/polybench/O0/small_dataset/deriche.wasm"),
        //File("./samples/NAS/O0/A/bt.wasm"),
    )
    for (sample in samples) {
        testUsingWAPC(sample)
    }
}

private fun testUsingWAPC(sample: File){
    val output = WAPC.compile(
        sample.toPath(),
        params = WAPC.Params(
            threads = 8,
            generateDotFiles = true,
            parallelize = true,
            parallelizeInnerLoops = true,
            normalizeLoops = true,
            enableAsserts = false,
            stripDebugNames = false,
            annotations = false,
            addCommentedIR = true,
        ),
    )
    println(WAPC.stats)

    var pout: String? = null
    println("running parallel...")
    val p_time = runTimed { pout = Wasmtime.runWithThreadsEnabled(output) }
    println(p_time)
    println(pout.orEmpty())


    var sout: String? = null
    println("running serial...")
    val s_time = runTimed { sout = Wasmtime.run(sample.toPath()) }
    println(s_time)
    println(sout.orEmpty())

    try {
        println("===== ${if (sout == pout) "Exact Match" else "Difference = ${PolybenchOutputComparator().compare(sout!!, pout!!)}"} =====")
    } catch (e: Exception){
        println("===== Exception =====")
    }
}

private fun testUsingWACK(sample: File){
    val output = WACK.run(sample.toPath())
    println(output)
}