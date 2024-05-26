import analysis.cfg.CFG
import analysis.dfa.Dfa
import generation.WasiThreadsGenerator
import generation.WatWriter
import ir.IRConstructor
import ir.statement.Function
import optimization.OptimizationPasses
import parser.Wat
import restructure.RestructurePasses
import java.io.File

fun main(args: Array<String>) {
    val samples = File("./samples").listFiles()
    for (sample in samples!!){
        val parseTree = Wat.parse(sample.path)
        val module = Wat.module(parseTree)
        val ir = IRConstructor(module)
        val program = ir.program()

        // intermediate
        program.statements.filterIsInstance<Function>().forEach { function ->
            /// cfg
            val cfg = CFG.from(function)
            cfg.writeToFile()

            /// dfa
            val dfa = Dfa.from(function, cfg)
            dfa.writeToFile()
        }


        // restructure pass
        RestructurePasses.all(program)

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
        val dotOut = File("./out/cfg_${test}_$functionIndex.dot")
        val dotWriter = dotOut.writer()
        cfg.dot(dotWriter)
        dotWriter.close()
    }
}
