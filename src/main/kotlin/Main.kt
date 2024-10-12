import java.io.File

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        //File("./samples/kernel_matrix_multiply.wasm"),
        //File("./samples/matrix_multiply.wasm"),
        //File("./src/test/resources/src/unknown_heap_allocated_kernel_call.wasm")
        File("./samples/ddt/gcd/gcd_test_2.wasm"),
        //File("./samples/polybench/O3/large_dataset/2mm.wasm"),
    )
    for (sample in samples) {
        val output = WAPC.compile(sample.toPath(), params = WAPC.Params(generateDotFiles = true, parallelize = true))
        /*val s_time = runTimed { Wasmtime.run(sample.toPath()) }
        println("running serial...")
        println(s_time)
        val p_time = runTimed { Wasmtime.runWithThreadsEnabled(output) }
        println("running parallel...")
        println(p_time)*/
    }
}

