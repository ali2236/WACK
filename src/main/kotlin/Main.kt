import analysis.Analysis
import generation.WasiThreadsGenerator
import generation.insureDirectoryExists
import ir.statement.Program
import optimization.OptimizationPasses
import java.io.File

fun main(args: Array<String>) {

    // create folders
    insureDirectoryExists("./out")
    insureDirectoryExists("./out/intermediate")

    // run
    val samples = listOf(File("./samples/matrix_multiply.wasm"))
    for (sample in samples) {
        val program = Program.from(sample)
        OptimizationPasses.apply(program)
        WasiThreadsGenerator().apply(program)
        Analysis.writeDotFiles(program, sample.nameWithoutExtension)
        program.exportAsWasm(File("./out/intermediate/wack_${sample.nameWithoutExtension}.wat"))
    }
}

