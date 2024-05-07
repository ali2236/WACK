package ir

import dev.aligator.parser.WatParser
import dev.aligator.parser.WatParserBaseVisitor
import ir.expression.*
import ir.expression.Assignment
import wasm.Index
import wasm.WasmModule

class FunctionVisitor(val module: WasmModule) : WatParserBaseVisitor<Unit>() {

    private val blocks = mutableListOf(Block(mutableListOf(), true))

    public val currentBlock: Block
        get() = blocks.last()

    val stack: Block
        get() = currentBlock

    private fun newScope() {
        blocks.add(Block())
    }

    private fun exitScope(): Block {
        return blocks.removeLast()
    }

    override fun visitBlock_instr(ctx: WatParser.Block_instrContext) {
        if (ctx.LOOP() != null) {
            newScope()
            super.visitBlock_instr(ctx)
            val block = exitScope()
            currentBlock.push(Loop(block))
        } else if (ctx.BLOCK() != null) {
            newScope()
            super.visitBlock_instr(ctx)
            val block = exitScope()
            currentBlock.push(block)
        }
    }

    override fun visitPlain_instr(ctx: WatParser.Plain_instrContext) {
        if (ctx.UNREACHABLE() != null) {
            // TODO: thorw trap
        } else if (ctx.BR() != null) {
            // TODO: Jump N blocks back
        } else if (ctx.BR_IF() != null) {
            // TODO: Jump N blocks back if != 0
            val depth = ctx.var_().first().text.toInt()
            val ifCondition = stack.pop()
            val ifBody = Block(
                mutableListOf(
                    Jump(depth, blocks[blocks.size - depth - 1])
                ),
                topLevel = false,
                brackets = false,
            )
            stack.push(If(ifCondition, ifBody))
        } else if (ctx.CONST() != null) {
            stack.push(Value(ctx.literal().text))
        } else if (ctx.CALL() != null) {
            val functionIndex = ctx.var_().first().text.toInt()
            val calledFunction = module.functions.first { it.index == Index(functionIndex) }
            val (paramsTypes, resultTypes) = calledFunction.type.getParamsAndResults(module)
            val params = paramsTypes.map { stack.pop() }
            val hasReturn = resultTypes.isNotEmpty()
            stack.push(FunctionCall("f${functionIndex}", params, hasReturn))
        } else if (ctx.LOCAL_GET() != null) {
            val symbol = ctx.var_().first().text
            stack.push(Value("l$symbol"))
        } else if (ctx.LOCAL_SET() != null) {
            val symbol = ctx.var_().first().text
            val value = stack.pop()
            stack.push(Assignment("l$symbol", value))
        } else if (ctx.LOCAL_TEE() != null) {
            val symbol = ctx.var_().first().text
            val value = stack.pop()
            stack.push(value)
            stack.push(Assignment("l$symbol", value))
        } else if (ctx.GLOBAL_GET() != null) {
            val symbol = ctx.var_().first().text
            stack.push(Value("g$symbol"))
        } else if (ctx.GLOBAL_SET() != null) {
            val symbol = ctx.var_().first().text
            stack.push(Assignment("g$symbol", stack.pop()))
        } else if (ctx.TEST() != null) {
            val operatorName = ctx.TEST()!!.text.substring(4)
            val expr = when (operatorName) {
                "eqz" -> BinaryOP("==", stack.pop(), Value("0"))
                else -> throw Error()
            }
            stack.push(expr)
        } else if (ctx.COMPARE() != null) {
            val operatorName = ctx.COMPARE()!!.text.substring(4)
            val operatorSign = when (operatorName) {
                "eq" -> "=="
                "ne" -> "!="
                "lt", "lt_s", "lt_u" -> "<"
                "le", "le_s", "le_u" -> "<="
                "gt", "gt_s", "gt_u" -> "<"
                "ge", "ge_s", "ge_u" -> ">="
                else -> operatorName
            }
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(operatorSign, first, second))
        } else if (ctx.BINARY() != null) {
            val operatorName = ctx.BINARY()!!.text.substring(4)
            val operatorSign = when (operatorName) {
                "add" -> "+"
                "sub" -> "-"
                "and" -> "&"
                "shl" -> "<<"
                else -> operatorName
            }
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(operatorSign, first, second))
        } else if (ctx.STORE() != null) {
            val data = stack.pop()
            val addr = stack.pop()
            stack.push(Store(data, addr))
        }
        return super.visitPlain_instr(ctx)
    }

}