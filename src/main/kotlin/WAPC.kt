import analysis.Analysis
import generation.WasiThreadsGenerator
import ir.statement.Program
import transform.TransformationPasses
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path

// WebAssembly Parallel Compiler
object WAPC {

    init {
        //Files.deleteIfExists(Path("./out"))
        Files.createDirectories(Path("./out/intermediate"))
    }

    fun compile(input: Path, output: Path? = null, generateDotFiles:Boolean = false): Path {
        val inputFile = input.toFile()
        val program = Program.from(inputFile)
        TransformationPasses.apply(program)
        WasiThreadsGenerator().apply(program)
        if(generateDotFiles){
            Analysis.writeDotFiles(program, inputFile.nameWithoutExtension)
        }
        val outputFile = output?.toFile() ?: File("./out/intermediate/wack_${inputFile.nameWithoutExtension}.wat")
        program.exportAsWasm(outputFile)
        return outputFile.toPath()
    }

}