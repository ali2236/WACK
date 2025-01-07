import compiler.WACK
import compiler.WAPC
import external.PolybenchOutputComparator
import external.WasmOpt
import external.Wasmtime
import external.runTimed
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.streams.toList

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        //File("./samples/languages/3mm.go.wasm"),
        //File("./src/test/resources/src/known_pre_allocated.wasm"),
        //File("./samples/transform/loop_normalization/loop_normalization.wasm"),
        //File("./samples/transform/reduction.wasm"),
        //File("./samples/ddt/gcd/gcd_test_2.wasm"),
        //File("./samples/simple_loop.wasm"),
        //File("./samples/polybench/O0/small_dataset/adi.wasm"),
        //File("./samples/NAS/O0/A/cg.wasm"),
        Path("./benchmark"),
    )
    for (sample in samples) {
        //testUsingWAPC(sample)
        compileFolder(sample)
        //compileFolder(sample, WAPC.Params(threads = 2))
        //compileFolder(sample, WAPC.Params(threads = 4))
        //compileFolder(sample, WAPC.Params(threads = 8))
        //compileFolder(sample, WAPC.Params(threads = 16))
        //compileFolder(sample, WAPC.Params(threads = 32))
        //compileFolder(sample, WAPC.Params(threads = 64))
        //compileFolder(sample, WAPC.Params(threads = 128))
    }
}

private fun testUsingWAPC(sample: File) {
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
            minimumLoopCost = 1000,
            reductionParallelization = true,
            multipleMemories = true,
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
        println(
            "===== ${
                if (sout == pout) "Exact Match" else "Difference = ${
                    PolybenchOutputComparator().compare(
                        sout!!,
                        pout!!
                    )
                }"
            } ====="
        )
        println("Speed up=${(s_time.inWholeMilliseconds / p_time.inWholeMilliseconds.toDouble())}x")
    } catch (e: Exception) {
        println("===== Exception =====")
    }
}

private fun testUsingWACK(sample: File) {
    val output = WACK.run(sample.toPath())
    println(output)
}

private fun compileFolder(dir: Path, params: WAPC.Params = WAPC.Params()) {
    Files.list(dir)
        .filter { Files.isRegularFile(it) }
        .filter { it.extension == "wasm" }
        .map { serialFile ->
            //val path = WAPC.compile(serialFile, params = params)
            Files.createDirectories(Path("./out/serial"))
            //val out = Path("./out/t${params.threads}/"+ path.toFile().name.substring(5))
            val out = Path("./out/serial/"+ serialFile.toFile().name)
            Files.copy(serialFile, out)
            WasmOpt.process(out.toFile())
        }.toList()
}