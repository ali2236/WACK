package external

import java.io.File

object W2C2 : FileProcessor {
    override fun process(input: File): File {
        // output path
        val output = File("./out/intermediate/${input.nameWithoutExtension}.c")

        // run process
        val process = ProcessBuilder("w2c2", "-p", input.absolutePath, output.path).start()
        
        process.waitFor()
        return output
    }
}