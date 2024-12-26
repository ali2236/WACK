package generation

import java.io.File

abstract class DotGraph {
    abstract fun dot(out: Appendable)

    abstract val graphName: String

    fun writeToFile(name: String) {
        val _name = name.replace(Regex("""/|\*"""), "_")
        File("./out/intermediate/$graphName").mkdir()
        val file = File("./out/intermediate/$graphName/$_name.dot")
        try {
            val dotWriter = file.writer()
            dot(dotWriter)
            dotWriter.close()
        } catch (e: Exception) {
            throw e
        }
    }
}