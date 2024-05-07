package ir

import dev.aligator.parser.WatParser
import dev.aligator.parser.WatParserBaseVisitor
import org.antlr.v4.runtime.tree.ParseTree
import wasm.Index
import wasm.WasmModule
import java.util.Stack

class FunctionVisitor(val module: WasmModule) : WatParserBaseVisitor<Unit>() {

    private val stacks = mutableListOf<Stack<Expression>>(Stack())

    public val stack : Stack<Expression>
        get() = stacks.last()

    private fun enterScope(){
        stacks.add(Stack())
    }

    private fun exitScope() : Block{
        return Block(stack.toList())
    }

    override fun visitBlock_instr(ctx: WatParser.Block_instrContext){
         if(ctx.LOOP() != null){
            enterScope()
             super.visitBlock_instr(ctx)
             val block = exitScope()
             stack.push(Loop(block))
         }
    }

    override fun visitPlain_instr(ctx: WatParser.Plain_instrContext) {
        if (ctx.CONST() != null) {
            stack.push(Value(ctx.literal().text))
        } else if(ctx.CALL() != null){
            val functionIndex = ctx.var_().first().text.toInt()
            val calledFunction = module.functions.first { it.index == Index(functionIndex) }
            val (paramsTypes, resultTypes) = calledFunction.type.getParamsAndResults(module)
            val params = paramsTypes.map { stack.pop() }
            val hasReturn = resultTypes.isNotEmpty()
            stack.push(FunctionCall("f${functionIndex}", params, hasReturn))
        } else if(ctx.LOCAL_GET() != null){
            val symbol = ctx.var_().first().text
            stack.push(Value("l$symbol"))
        } else if(ctx.LOCAL_SET() != null){
            val symbol = ctx.var_().first().text
            stack.push(Assignment("l$symbol", stack.pop()))
        } else if(ctx.LOCAL_TEE() != null){
            val symbol = ctx.var_().first().text
            val value = stack.pop()
            stack.push(Assignment("l$symbol", value))
            stack.push(value)
        } else if(ctx.GLOBAL_GET() != null){
            val symbol = ctx.var_().first().text
            stack.push(Value("g$symbol"))
        } else if(ctx.GLOBAL_SET() != null){
            val symbol = ctx.var_().first().text
            stack.push(Assignment("g$symbol", stack.pop()))
        } else if(ctx.BINARY() != null){
            val operatorName = ctx.BINARY()!!.text.substring(4)
            val operatorSign = when(operatorName){
                "add" -> "+"
                "sub" -> "-"
                "and" -> "&"
                "shl" -> "<<"
                else -> operatorName
            }
            stack.push(BinaryOP(operatorSign, stack.pop(), stack.pop()))
        } else if(ctx.STORE() != null){
            stack.push(Store(stack.pop(), stack.pop()))
        }
        return super.visitPlain_instr(ctx)
    }

}