import java.io.File

fun main(args: Array<String>) {
    // run
    val samples = listOf(
        File("./samples/kernel_matrix_multiply.wasm"),
        //File("./samples/matrix_multiply.wasm"),
        //File("./samples/polybench/2mm.wasm"),
    )
    for (sample in samples) {
        WAPC.compile(sample.toPath(), generateDotFiles = true)
    }
}

