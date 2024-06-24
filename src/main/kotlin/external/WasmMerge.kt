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
        val commands = mutableListOf("wasm-merge", "-all", "--rename-export-conflicts")
        modules.forEach {
            commands.add(it.second.absolutePath)
            commands.add(it.first)
        }
        commands.add("-o")
        commands.add(output.absolutePath)
        val process = ProcessBuilder(commands).start()
        process.waitFor()
    }
}