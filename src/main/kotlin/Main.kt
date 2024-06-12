import analysis.cfg.CFG
import analysis.dfa.Dfa
import external.Wasm2Wat
import generation.WasiThreadsGenerator
import generation.WatWriter
import ir.IRConstructor
import ir.statement.Function
import ir.statement.Program
import optimization.OptimizationPasses
import ir.parser.Wat
import java.io.File

fun main(args: Array<String>) {

    // create folders
    val _out = File("./out")
    if(!_out.exists()){
        _out.mkdir()
    }
    val _intermediate = File("./out/intermediate")
    if(!_intermediate.exists()){
        _intermediate.mkdir()
    }

    // run
    val wasm2wat = Wasm2Wat()
    val samples = listOf(File("./samples/matrix_multiply.wasm"))// File("./samples").listFiles()
    for (sample in samples!!) {
        val watInput = wasm2wat.process(sample)
        val parseTree = Wat.parse(watInput.path)
        val tokensFile = File("./out/tokens.txt").also { it.createNewFile() }
        val tokensWriter = tokensFile.bufferedWriter()
        for (i in 0 until parseTree.childCount){
            tokensWriter.write(parseTree.getChild(i).text)
            tokensWriter.newLine()
        }
        val module = Wat.module(parseTree)
        val ir = IRConstructor(module)
        val program = ir.program()

        OptimizationPasses.apply(program)

        analysis2Dot(program, watInput)

        // runtime injection / parallel loop transformer
        WasiThreadsGenerator().apply(program)

        // write out
        val watOut = File("./out/wat_${watInput.nameWithoutExtension}.wat")
        val outWriter = watOut.writer()
        val watWriter = WatWriter(outWriter)
        program.wat(watWriter)
        outWriter.flush()
        outWriter.close()
    }
}

fun analysis2Dot(program: Program, sample: File) {
    // intermediate outputs
    program.statements.filterIsInstance<Function>().forEach { function ->
        val fileName = "${sample.nameWithoutExtension}_f" + function.functionData.index

        /// cfg
        val cfg = CFG.from(function)
        cfg.writeToFile(fileName)

        /// dfa
        val dfa = Dfa.from(function, cfg)
        dfa.writeToFile(fileName)
    }
}
