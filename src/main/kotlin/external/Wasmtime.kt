package external

import java.nio.file.Path

object Wasmtime {

    fun runWithThreadsEnabled(path: Path) : String {
        return run(path, listOf("-W", "all-proposals=y", "-S", "threads"))
    }

    fun run(path: Path, flags: List<String> = listOf()) : String{
        val process = ProcessBuilder(
            listOf("wasmtime") + flags + listOf(path.toString())
        ).start()
        process.errorStream.reader().forEachLine {
          throw Exception(it)
        }
        val reader = process.inputStream.bufferedReader()
        val output = StringBuilder()
        var line = reader.readLine()
        while (line != null) {
            output.appendLine(line)
            line = reader.readLine()
        }

        process.waitFor()
        return output.toString()
    }

}