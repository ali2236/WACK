package external

import java.io.File
import java.lang.Exception

class Wasm2Wat : FileProcessor {
    override fun process(input: File): File {
        // validate input
        if (input.extension != "wasm") {
            return input
        }

        // TODO: validate wasm2wat is present

        // make output file
        val output = File("./out/intermediate/${input.nameWithoutExtension}.wat")
        if (output.exists()) {
            output.delete()
        }
        output.createNewFile()


        // run process
        val process = ProcessBuilder("wasm2wat", input.absolutePath).start()
        val reader = process.inputStream.bufferedReader()
        val writer = output.bufferedWriter()
        var line = reader.readLine()
        while (line != null) {
            writer.write(line)
            writer.newLine()
            line = reader.readLine()
        }
        writer.flush()
        writer.close()

        process.waitFor()
        return output
    }
}