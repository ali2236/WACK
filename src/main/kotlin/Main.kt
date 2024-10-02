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
        val output = WAPC.compile(sample.toPath(), generateDotFiles = true)
        /*val time = runTimed { Wasmtime.runWithThreadsEnabled(output) }
        println("running...")
        println(time)*/
    }
}

