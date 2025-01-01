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
        //File("./src/test/resources/src/known_pre_allocated.wasm"),
        //File("./samples/transform/loop_normalization/loop_normalization.wasm"),
        //File("./samples/transform/reduction.wasm"),
        //File("./samples/simple_loop.wasm"),
        File("./samples/polybench/O0/small_dataset/adi.wasm"),
        //File("./samples/NAS/O0/A/cg.wasm"),
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
            addCommentedIR = false,
            minimumLoopCost = 0,
            reductionParallelization = true,
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
        println("Speed up=${(s_time.inWholeMilliseconds/p_time.inWholeMilliseconds.toDouble())}x")
    } catch (e: Exception){
        println("===== Exception =====")
    }
}

private fun testUsingWACK(sample: File){
    val output = WACK.run(sample.toPath())
    println(output)
}