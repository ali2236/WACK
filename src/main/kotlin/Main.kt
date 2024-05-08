import dev.aligator.parser.WatParser
import ast.IRConstructor
import parser.Wat
import refinment.RefinerPasses

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



fun printInstructions(instructions: MutableList<WatParser.InstrContext>){
    instructions.forEach {
        it.block_instr()?.let { b ->
            println("Block(")
            printInstructions(b.block().instr_list().instr())
            println(")")
        }
        it.expr()?.let {e ->
            println("expr(${e.text})")
        }
        it.call_instr_instr()?.let {
            println("call")
        }
        it.plain_instr()?.let {p ->
            println("plain(${p.text})")
        }
    }
}

