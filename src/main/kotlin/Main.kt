import analysis.cfg.CfgBuilder
import generation.WatWriter
import ir.IRConstructor
import ir.statement.Function
import optimization.OptimizationPasses
import parser.Wat
import restructure.RestructurePasses
import java.io.File
import java.lang.Appendable

fun main(args: Array<String>) {
    val file = listOf(Pair("seq", 2), Pair("simple_loop", 1))
    for ((test, functionIndex) in file){
        val sample = "./samples/$test.wat"

        val parseTree = Wat.parse(sample)
        val module = Wat.module(parseTree)
        val ir = IRConstructor(module)
        val program = ir.program()

        // restructure pass
        RestructurePasses.all(program)

        // analysis passes

        // optimization passes
        //OptimizationPasses.apply(program)

        // code generation
        val watOut = File("./out/wat_$test.wat")
        val outWriter = watOut.writer()
        val watWriter = WatWriter(outWriter)
        program.wat(watWriter)
        outWriter.flush()
        outWriter.close()

        val cfg = CfgBuilder(program.statements.filterIsInstance<Function>()[functionIndex]).build()

        // cfg
        val dotOut = File("./out/cfg_${test}_$functionIndex.dot")
        val dotWriter = dotOut.writer()
        cfg.dot(dotWriter)
        dotWriter.close()
    }
}

