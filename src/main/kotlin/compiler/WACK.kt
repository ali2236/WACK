package compiler

import external.Wasm2C
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

    override fun run(input: Path, output: Path?): Path {
        if (!input.exists()) {
            throw Error("Input File doesn't Exist!");
        }
        val inputFile = input.toFile()
        // decompile using w2c2
        val cFile = Wasm2C.process(inputFile)
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

        // find function with range loops
        val functions = program.statements
            .filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }




        // Problem: replaced normal code doesn't work with other w2c2 code
        // run a source-to-source compiler
        // Problem: source to source compilers don't like w2c2 c format
        // link with openmp
        // compile to wasm

        return cFile.toPath()
    }

}