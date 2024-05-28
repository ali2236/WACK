import analysis.cfg.CFG
import analysis.dfa.Dfa
import ir.IRConstructor
import ir.statement.Function
import optimization.OptimizationPasses
import parser.Wat
import restructure.RestructurePasses
import java.io.File

fun main(args: Array<String>) {
    val samples = listOf(File("./samples/simple_loop.wat"))// File("./samples").listFiles()
    for (sample in samples!!){
        val parseTree = Wat.parse(sample.path)
        val module = Wat.module(parseTree)
        val ir = IRConstructor(module)
        val program = ir.program()

        RestructurePasses.basic(program)

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

        // restructure pass
        // RestructurePasses.all(program)

        // analysis passes

        // optimization passes
        OptimizationPasses.apply(program)

        // code generation
        //WasiThreadsGenerator().apply(program)

        // write out
        //val watOut = File("./out/wat_$test.wat")
        //val outWriter = watOut.writer()
        //val watWriter = WatWriter(outWriter)
        //program.wat(watWriter)
        //outWriter.flush()
        //outWriter.close()



        // cfg
        /*val dotOut = File("./out/cfg_${test}_$functionIndex.dot")
        val dotWriter = dotOut.writer()
        cfg.dot(dotWriter)
        dotWriter.close()*/
    }
}
