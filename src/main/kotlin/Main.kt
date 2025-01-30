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
        //File("./src/test/resources/src/known_pre_allocated.wasm"),
        //File("./samples/transform/loop_normalization/loop_normalization_2.wasm"),
        //File("./samples/transform/reduction.wasm"),
        //File("./samples/ddt/gcd/gcd_test_2.wasm"),
        //File("./samples/simple_loop.wasm"),
        File("./samples/polybench/O0/large_dataset/adi.wasm"),
        //Path("./benchmark"),
    )
    for (sample in samples) {
        testUsingWAPC(sample)
        //compileFolder(sample)
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
            annotations = true,
            addCommentedIR = true,
            minimumLoopCost = 0,
            reductionParallelization = true,
            multipleMemories = true,
            dependenceTest = WAPC.DependenceTest.miv,
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

private fun compileFolder(dir: Path, params: WAPC.Params = WAPC.Params()) {
    Files.list(dir)
        .filter { Files.isRegularFile(it) }
        .filter { it.extension == "wasm" }
        .map { serialFile ->
            val path = WAPC.compile(serialFile, params = params)
            Files.createDirectories(Path("./out/serial"))
            Files.createDirectories(Path("./out/t${params.threads}"))
            val outp = Path("./out/t${params.threads}/"+ path.toFile().name.substring(5))
            val outs = Path("./out/serial/"+ serialFile.toFile().name)
            Files.copy(serialFile, outs)
            Files.move(path, outp)
            WasmOpt.process(outs.toFile())
            WasmOpt.process(outp.toFile())
        }.toList()
}