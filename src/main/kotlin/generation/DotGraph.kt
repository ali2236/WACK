package generation

import java.io.File

abstract class DotGraph {
    abstract fun dot(out: Appendable)

    abstract val graphName: String

    fun writeToFile(name: String){
        File("./out/intermediate/$graphName").mkdir()
        val file = File("./out/intermediate/$graphName/$name.dot")
        val dotWriter = file.writer()
        dot(dotWriter)
        dotWriter.close()
    }
}