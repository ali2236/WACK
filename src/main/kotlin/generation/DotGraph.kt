package generation

import java.io.File

abstract class DotGraph {
    abstract fun dot(out: Appendable)

    fun writeToFile(file: File){
        val file = File("./out/intermediate/_,_.dot")// TODO
        val dotWriter = file.writer()
        dot(dotWriter)
        dotWriter.close()
    }
}