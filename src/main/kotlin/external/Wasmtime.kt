package external

import java.nio.file.Path

object Wasmtime {

    fun runWithThreadsEnabled(path: Path) {
        run(path, listOf("-W all-proposals=y", "-S threads"))
    }

    fun run(path: Path, flags: List<String> = listOf()) {
        val process = ProcessBuilder(
            listOf("wasmtime") + flags + listOf(path.toString())
        ).start()
    }

}