package external

import java.nio.file.Path
import java.util.stream.Stream
import kotlin.concurrent.thread

object Wasmtime {

    fun runWithThreadsEnabled(path: Path): String {
        return run(path, listOf("-W", "all-proposals=y", "-S", "threads", "-D", "coredump=./trap.coredump"))
    }

    fun run(path: Path, flags: List<String> = listOf()): String {
        val command = listOf("wasmtime") + flags + listOf(path.toString())
        //println(command.joinToString(" "))
        val process = ProcessBuilder(command).start()
        val stderr = process.errorStream.bufferedReader().lines()
        val stdin = process.inputStream.bufferedReader().lines()
        val input = Stream.concat(stdin, stderr)
        val output = StringBuilder()
        thread {
            input.forEach(output::appendLine)
        }

        process.waitFor()
        return output.toString()
    }

}