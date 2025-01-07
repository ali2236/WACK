package external

import java.io.File

// wasm-opt -O1 reduction_c.wasm -o reduction.wasm
object WasmOpt : FileProcessor {

    override fun process(input: File): File {
        // validate input
        if (input.extension != "wasm") {
            return input
        }

        // run process
        val output = input
        val process = ProcessBuilder("wasm-opt", "-O3", input.absolutePath, "-o", output.absolutePath).start()
        process.waitFor()
        return output
    }


}