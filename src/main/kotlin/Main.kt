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
    val (test, functionIndex) = Pair("scalar", 1)// Pair("simple_loop", 1)
    val sample = "./samples/$test.wat"

    val parseTree = Wat.parse(sample)
    val module = Wat.module(parseTree)
    val ir = IRConstructor(module)
    val program = ir.program()

    // restructure pass
    RestructurePasses.all(program)

    // analysis passes

    // optimization passes
    OptimizationPasses.apply(program)

    // code generation


    //val cfg = CfgBuilder(program.statements.filterIsInstance<Function>()[functionIndex]).build()

    // wat
    val watOut = File("./out/wat_$test.wat")
    val outWriter = watOut.writer()
    val watWriter = WatWriter(outWriter)
    program.wat(watWriter)
    outWriter.flush()
    outWriter.close()

    // ir
    /*val cOut = File("./out/c_$test.c")
    val cWriter = cOut.writer()
    program.write(cWriter)
    cWriter.close()*/

    // cfg
    /*val dotOut = File("./out/cfg_${test}_$functionIndex.dot")
    val dotWriter = dotOut.writer()
    cfg.dot(dotWriter)
    dotWriter.close()*/
}

