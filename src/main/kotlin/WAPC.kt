import analysis.Analysis
import generation.WasiThreadsGenerator
import ir.statement.Program
import transform.TransformationPasses
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists

// WebAssembly Parallel Compiler
object WAPC {

    init {
        //Files.deleteIfExists(Path("./out"))
        Files.createDirectories(Path("./out/intermediate"))
    }

    var params: Params? = null

    fun compile(input: Path, output: Path? = null, params: Params = Params()): Path {
        this.params = params
        try {
            if(!input.exists()){
                throw Error("Input File doesn't Exist!");
            }
            val inputFile = input.toFile()
            val program = Program.from(inputFile)
            TransformationPasses.apply(program)
            WasiThreadsGenerator().apply(program)
            if (params.generateDotFiles) {
                Analysis.writeDotFiles(program, inputFile.nameWithoutExtension)
            }
            val outputFile = output?.toFile() ?: File("./out/intermediate/wack_${inputFile.nameWithoutExtension}.wat")
            val wasmFile = program.exportAsWasm(outputFile)
            return wasmFile.toPath()
        } finally {
            this.params = null
        }
    }

    data class Params(
        val parallelize : Boolean = true,
        val parallelizeInnerLoops: Boolean = false,
        val threads: Int = 8,
        val generateDotFiles: Boolean = false,
        val dfaStatementId: Boolean = false,
        val dfaShowAlias: Boolean = true,
        val normalizeLoops: Boolean = true,
    )

}