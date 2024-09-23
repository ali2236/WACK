package external

import java.nio.file.Path

class Make(val path: Path) {

    fun run() {
        val process = ProcessBuilder(
            listOf("make")
        ).directory(path.toFile())
            .start()
        process.waitFor()
    }
}