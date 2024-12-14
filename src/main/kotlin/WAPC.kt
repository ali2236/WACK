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
            /*val mergedFile = WasmMerge.merge(listOf(
                Pair("wack", wasmFile),
                Pair("print", File("./runtime/print.wasm"))
            ), "merged")*/
            return wasmFile.toPath()
        } finally {
            this.params = null
        }
    }

    /*fun compileWACK(input: Path, output: Path? = null, params: Params = Params()): Path {
        this.params = params
        try {
            if(!input.exists()){
                throw Error("Input File doesn't Exist!");
            }
            val inputFile = input.toFile()
            val program = Program.from(inputFile)
            // decompile using w2c2
                // Problem: dosen't support all APIs
            // replace kernel functions with self generated code
                // Problem: replaced normal code doesn't work with other w2c2 code
            // run a source-to-source compiler
                // Problem: source to source compilers don't like w2c2 c format
            // link with openmp
            // compile to wasm
        } finally {
            this.params = null
        }
    }*/

    data class Params(
        val parallelize : Boolean = true,
        val parallelizeInnerLoops: Boolean = true,
        val threads: Int = 8,
        val generateDotFiles: Boolean = false,
        val dfaStatementId: Boolean = false,
        val dfaShowAlias: Boolean = true,
        val normalizeLoops: Boolean = true,
        val enableAsserts: Boolean = false,
        val addCommentedIR: Boolean = true,
        val multipleMemories: Boolean = true,
        val annotations: Boolean = true,
        val threadSpawnModule: String = "wasi" /*"wasi_snapshot_preview1"*/,
        val stripDebugNames : Boolean = true,
    )

}