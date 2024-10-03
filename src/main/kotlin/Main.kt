import external.Wasmtime
import external.runTimed
import java.io.File

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        //File("./samples/kernel_matrix_multiply.wasm"),
        //File("./samples/matrix_multiply.wasm"),
        //File("./src/test/resources/src/unknown_heap_allocated_kernel_call.wasm")
        File("./samples/polybench/adi.wasm"),
    )
    for (sample in samples) {
        val output = WAPC.compile(sample.toPath(), params = WAPC.Params(generateDotFiles = true))
        val s_time = runTimed { Wasmtime.run(sample.toPath()) }
        println("running serial...")
        println(s_time)
        val p_time = runTimed { Wasmtime.runWithThreadsEnabled(output) }
        println("running parallel...")
        println(p_time)
    }
}

