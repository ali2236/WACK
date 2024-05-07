import dev.aligator.parser.WatParser
import dev.aligator.parser.WatParserBaseVisitor
import parser.Wat
import java.util.LinkedList
import java.util.Stack

fun main(args: Array<String>) {
    val sample = "./samples/seq.wat"

    val parseTree = Wat.parse(sample)
    val module = Wat.module(parseTree)

    FunctionVisitor().visit(module.functions[2].code)
}

class FunctionVisitor : WatParserBaseVisitor<Unit>() {

    val stack = Stack<Any>()

    override fun visitInstr(ctx: WatParser.InstrContext) {
        if(ctx.plain_instr() != null){
            println(ctx.text)
        } else if (ctx.block_instr() != null) {
            println("block")
        }
    }

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

