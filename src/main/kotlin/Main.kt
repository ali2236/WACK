import ir.IRConstructor
import parser.Wat
import restructure.RefinerPasses

fun main(args: Array<String>) {
    val sample = "./samples/seq.wat"

    val parseTree = Wat.parse(sample)
    val module = Wat.module(parseTree)
    val ir = IRConstructor(module)
    val program = ir.program()
    RefinerPasses.all(program)

    val buffer = StringBuffer()
    program.c(buffer)
    println(buffer)
}

