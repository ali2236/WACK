import analysis.Analysis
import analysis.cfg.CFG
import analysis.dfa.Dfa
import external.Wasm2Wat
import external.Wat2Wasm
import generation.PThreadsGenerator
import generation.WasiThreadsGenerator
import generation.WatWriter
import ir.IRConstructor
import ir.annotations.Skip
import ir.parser.Wat
import ir.statement.Function
import ir.statement.Program
import optimization.OptimizationPasses
import java.io.File

fun main(args: Array<String>) {

    // create folders
    insureDirectoryExists("./out")
    insureDirectoryExists("./out/intermediate")

    // run
    val samples = listOf(File("./samples/matrix_multiply.wasm"))// File("./samples").listFiles()
    for (sample in samples) {
        val program = Program.from(sample)

        OptimizationPasses.apply(program)

        // runtime injection / parallel loop transformer
        //WasiThreadsGenerator().apply(program)
        PThreadsGenerator(sample.nameWithoutExtension).apply(program)

        // Analysis.writeDotFiles(program, sample.nameWithoutExtension)

        // convert to wasm / wat
        program.exportAsWasm(File("./out/intermediate/wack_${sample.nameWithoutExtension}.wat"))
    }
}

fun insureDirectoryExists(path: String) {
    val dir = File(path)
    if (!dir.exists()) {
        dir.mkdir()
    }
}
