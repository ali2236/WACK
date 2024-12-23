package compiler

import external.Wasm2C
import generation.c.CWriter
import ir.annotations.Skip
import ir.statement.Function
import ir.statement.Program
import transform.*
import transform.constant_propegation.ConstantPropagation
import transform.restructure.ConditionRestructure
import transform.restructure.ConditionalLoopRestructure
import transform.restructure.IncrementRestructure
import transform.restructure.RangeLoopRestructure
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists

// WebAssembly 2 C Kompiler
object WACK : WebAssemblyCompiler {

    init {
        Files.createDirectories(Path("./out/intermediate"))
    }

    var params = Params()

    override fun run(input: Path, output: Path?): Path {
        if (!input.exists()) {
            throw Error("Input File doesn't Exist!");
        }
        val inputFile = input.toFile()
        // decompile using w2c2
        //val cFile = Wasm2C.process(inputFile)
        // Problem: doesn't support all APIs
        // Alternative: Even wasm2c generated code is barely analysable
        // replace kernel functions with self generated code
        val program = Program.from(inputFile)
        val passes = listOf<Transformer>(
            SkipNoLoopFunctions(),
            ConstantPropagation(),
            ConditionRestructure(),
            ConditionalLoopRestructure(),
            IncrementRestructure(),
            RangeLoopRestructure(),
            SkipNoRangeLoopFunctions(),
        )
        for (pass in passes){
            pass.apply(program)
        }

        // convert to c
        val buffer = StringBuilder()
        val cWriter = CWriter(buffer)
        val functions = program.statements
            .filterIsInstance<Function>()



        println(functions)


        // Problem: replaced normal code doesn't work with other w2c2 code
        // run a source-to-source compiler
        // Problem: source to source compilers don't like w2c2 c format
        // link with openmp
        // compile to wasm

        return Path(".")
    }

    data class Params(
        val parallelize : Boolean = true,
        //val parallelizeInnerLoops: Boolean = true,
        //val threads: Int = 8,
        //val generateDotFiles: Boolean = false,
        //val dfaStatementId: Boolean = false,
        //val dfaShowAlias: Boolean = true,
        //val normalizeLoops: Boolean = true,
        //val enableAsserts: Boolean = false,
        val addCommentedIR: Boolean = true,
        //val multipleMemories: Boolean = true,
        //val annotations: Boolean = true,
        //val threadSpawnModule: String = "wasi" /*"wasi_snapshot_preview1"*/,
        //val stripDebugNames : Boolean = true,
    )

}