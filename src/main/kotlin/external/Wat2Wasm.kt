package external

import ir.Mode
import java.io.File

// wat2wasm.exe .\wat_seq.wat --enable-threads --enable-multi-memory
class Wat2Wasm : FileProcessor {
    override fun process(input: File): File {
        // validate input
        if (input.extension != "wat") {
            return input
        }

        // TODO: validate wat2wasm is present

        // make output file
        val output = File("./out/${input.nameWithoutExtension}.wasm")
        if (output.exists()) {
            output.delete()
        }


        // run process
        val commands = mutableListOf<String>()
        commands.add("wat2wasm")
        commands.add(input.absolutePath)
        commands.add("--enable-threads")
        if (Mode.multipleMemories) {
            commands.add("--enable-multi-memory")
        }
        if(Mode.annotations){
            commands.add("--enable-annotations")
        }
        commands.add("-o")
        commands.add(output.absolutePath)
        val process = ProcessBuilder(commands).start()

        val errors = process.errorStream.reader()
        errors.forEachLine {
            println(it)
        }

        process.waitFor()
        return output
    }
}