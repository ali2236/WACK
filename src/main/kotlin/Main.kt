import analysis.Analysis
import analysis.cfg.CFG
import analysis.dfa.Dfa
import external.Wasm2Wat
import external.Wat2Wasm
import generation.WatWriter
import ir.IRConstructor
import ir.annotations.Skip
import ir.parser.Wat
import ir.statement.Function
import ir.statement.Program
import java.io.File

fun main(args: Array<String>) {

    // create folders
    insureDirectoryExists("./out")
    insureDirectoryExists("./out/intermediate")

    // run
    val samples = listOf(File("./samples/go_matrix_multiply.wasm"))// File("./samples").listFiles()
    for (sample in samples) {
        val watInput = Wasm2Wat.process(sample)
        val parseTree = Wat.parse(watInput.path)
        val module = Wat.module(parseTree)
        val ir = IRConstructor(module)
        val program = ir.program()

        // OptimizationPasses.apply(program)

        // runtime injection / parallel loop transformer
        // WasiThreadsGenerator().apply(program)

        Analysis.writeDotFiles(program, watInput.nameWithoutExtension)

        // write out
        val watOut = File("./out/intermediate/wack_${watInput.nameWithoutExtension}.wat")
        WatWriter.writeToFile(program, watOut)

        // convert to wasm
        Wat2Wasm().process(watOut)
    }
}

fun insureDirectoryExists(path: String) {
    val dir = File(path)
    if (!dir.exists()) {
        dir.mkdir()
    }
}
