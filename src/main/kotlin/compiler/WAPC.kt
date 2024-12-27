package compiler

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
object WAPC : WebAssemblyCompiler {

    init {
        Files.createDirectories(Path("./out/intermediate"))
    }

    var stats : Statistics = Statistics()
    var params: Params = Params()

    fun compile(input: Path, output: Path? = null, params: Params = Params()): Path {
        WAPC.params = params
        stats = Statistics()
        try {
            return run(input, output)
        } finally {
            WAPC.params = Params()
        }
    }

    override fun run(input: Path, output: Path?): Path {
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
    }



    data class Params(
        val parallelize : Boolean = true,
        val parallelizeInnerLoops: Boolean = true,
        val threads: Int = 8,
        val generateDotFiles: Boolean = false,
        val dfaStatementId: Boolean = false,
        val dfaShowAlias: Boolean = true,
        val normalizeLoops: Boolean = true,
        val enableAsserts: Boolean = false,
        val addCommentedIR: Boolean = false,
        val multipleMemories: Boolean = true,
        val annotations: Boolean = false,
        val threadSpawnModule: String = "wasi" /*"wasi_snapshot_preview1"*/,
        val stripDebugNames : Boolean = true,
        val minimumLoopCost : Int = 100000,
        val reductionParallelization: Boolean = false,
    )

    data class Statistics(
        var topLevelRangeLoops : Int = 0,
        var loopsParallelized : Int = 0,
    )

}