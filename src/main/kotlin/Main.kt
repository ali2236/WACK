import analysis.cfg.CfgBuilder
import ir.IRConstructor
import ir.statement.Function
import parser.Wat
import restructure.ShiftToMultiply
import java.io.File

fun main(args: Array<String>) {
    val test = "seq"
    val functionIndex = 2
    val sample = "./samples/$test.wat"

    val parseTree = Wat.parse(sample)
    val module = Wat.module(parseTree)
    val ir = IRConstructor(module)
    val program = ir.program()

    // restructure pass
    ShiftToMultiply().run(program)

    val cfg = CfgBuilder(program.statements.filterIsInstance<Function>()[functionIndex]).build()

    //RefinerPasses.all(program)

    // c
    val cOut = File("./out/c_$test.c")
    val cWriter = cOut.writer()
    program.write(cWriter)
    cWriter.close()

    // cfg
    val dotOut = File("./out/cfg_${test}_$functionIndex.dot")
    val dotWriter = dotOut.writer()
    cfg.dot(dotWriter)
    dotWriter.close()
}

