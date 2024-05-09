package ir

import dev.aligator.parser.WatParser
import dev.aligator.parser.WatParserBaseVisitor
import ir.expression.*
import ir.statement.*
import wasm.Index
import wasm.WasmModule

class FunctionVisitor(val module: WasmModule, firstBlock: Block) : WatParserBaseVisitor<Unit>() {

    private val blocks = mutableListOf(firstBlock)

    private val currentBlock: Block
        get() = blocks.last()

    val stack: Block
        get() = currentBlock

    private fun newScope(loop: Boolean = false) {
        if (loop) {
            blocks.add(Loop())
        } else {
            blocks.add(Block())
        }
    }

    private fun exitScope(): Block {
        val block = blocks.removeLast()
        block.close()
        return block
    }

    override fun visitBlock_instr(ctx: WatParser.Block_instrContext) {
        if (ctx.LOOP() != null) {
            newScope(loop = true)
            super.visitBlock_instr(ctx)
            val loop = exitScope() as Loop
            currentBlock.push(loop)
        } else if (ctx.BLOCK() != null) {
            newScope()
            super.visitBlock_instr(ctx)
            val block = exitScope()
            currentBlock.push(block)
        }
    }

    override fun visitPlain_instr(ctx: WatParser.Plain_instrContext) {
        if (ctx.UNREACHABLE() != null) {
            stack.push(Unreachable())
        } else if (ctx.BR() != null) {
            // TODO: Jump N blocks back
        } else if (ctx.BR_IF() != null) {
            val depth = ctx.var_().first().text.toInt()
            val ifCondition = stack.pop()
            val target = blocks[blocks.size - depth - 1]
            val ifBody = if (target is Loop && depth == 0) {
                Continue()
            } else if (target is Block && depth == 0) {
                Break()
            } else {
                throw Error()
            }
            stack.push(BrIf(ifCondition, ifBody, depth))
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
            val symbol = Symbol("l" + ctx.var_().first().text)
            stack.push(symbol)
        } else if (ctx.LOCAL_SET() != null) {
            val symbol = Symbol("l" + ctx.var_().first().text)
            val value = stack.pop()
            stack.push(Assignment(symbol, value))
        } else if (ctx.LOCAL_TEE() != null) {
            val symbol = Symbol("l" + ctx.var_().first().text)
            val value = stack.pop()
            val dependant = value.symbols().any { it == symbol }
            if (dependant) {
                stack.push(symbol)
            } else {
                stack.push(value)
            }
            stack.push(Assignment(symbol, value))
        } else if (ctx.GLOBAL_GET() != null) {
            val symbol = Symbol("g" + ctx.var_().first().text)
            stack.push(symbol)
        } else if (ctx.GLOBAL_SET() != null) {
            val symbol = Symbol("g" + ctx.var_().first().text)
            stack.push(Assignment(symbol, stack.pop()))
        } else if (ctx.TEST() != null) {
            val operatorName = ctx.TEST()!!.text.substring(4)
            val expr = when (operatorName) {
                "eqz" -> BinaryOP(Operator.eq, stack.pop(), Value("0"))
                else -> throw Error()
            }
            stack.push(expr)
        } else if (ctx.COMPARE() != null) {
            val operatorName = ctx.COMPARE()!!.text.substring(4)
            val operatorSign = when (operatorName) {
                "eq" -> Operator.eq
                "ne" -> Operator.neq
                "lt", "lt_s", "lt_u" -> Operator.lt
                "le", "le_s", "le_u" -> Operator.le
                "gt", "gt_s", "gt_u" -> Operator.gt
                "ge", "ge_s", "ge_u" -> Operator.ge
                else -> throw Error()
            }
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(operatorSign, first, second))
        } else if (ctx.BINARY() != null) {
            val operatorName = ctx.BINARY()!!.text.substring(4)
            val operatorSign = when (operatorName) {
                "add" -> Operator.add
                "sub" -> Operator.sub
                "and" -> Operator.and
                "shl" -> Operator.shl
                else -> throw Error()
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