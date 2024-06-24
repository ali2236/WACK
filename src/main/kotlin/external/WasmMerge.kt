package external

import java.io.File

object WasmMerge {
    fun merge(modules: List<Pair<String, File>>, outputName: String){
        // validate input
        if (modules.map { it.second }.any { it.extension != "wasm" }) {
            throw Exception()
        }

        // make output file
        val output = File("./out/$outputName.wasm")
        if (output.exists()) {
            output.delete()
        }


        // run process
        val processBuilder = ProcessBuilder("wasm-merge", "-all", "--rename-export-conflicts")
        modules.forEach {
            processBuilder.command(it.second.absolutePath)
            processBuilder.command(it.first)
        }
        processBuilder.command("-o", output.absolutePath)
        val process = processBuilder.start()
        process.waitFor()
    }
}