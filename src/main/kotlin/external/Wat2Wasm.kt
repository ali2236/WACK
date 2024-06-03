package external

import java.io.File

// wat2wasm.exe .\wat_seq.wat --enable-threads --enable-multi-memory
class Wat2Wasm : FileProcessor {
    override fun process(input: File) : File{
        // validate input
        if(input.extension != "wat"){
            return input
        }

        // TODO: validate wat2wasm is present

        // make output file
        val output = File("./out/intermediate/wack_${input.nameWithoutExtension}.wasm")
        if(output.exists()){
            output.delete()
        }


        // run process
        val process = ProcessBuilder(
            "wat2wasm",
            input.absolutePath,
            "--enable-threads",
            "--enable-multi-memory"
        ).start()

        process.waitFor()
        return output
    }
}