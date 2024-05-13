package ir

import dev.aligator.parser.WatParser
import dev.aligator.parser.WatParserBaseVisitor
import ir.expression.*
import ir.statement.*
import wasm.Index
import wasm.WasmFunction
import wasm.WasmModule
import wasm.WasmValueType
import java.lang.Exception

class FunctionVisitor(val module: WasmModule, val function: WasmFunction, firstBlock: Block) : WatParserBaseVisitor<Unit>() {

    private val blocks = mutableListOf(firstBlock)

    private val currentBlock: Block
        get() = blocks.last()

    val stack: Block
        get() = currentBlock

    private fun newScope(block: Block) {
        blocks.add(block)
    }

    private fun exitScope(): Block {
        val block = blocks.removeLast()
        block.close()
        return block
    }

    override fun visitBlock_instr(ctx: WatParser.Block_instrContext) {
        if (ctx.LOOP() != null) {
            newScope(Loop())
            super.visitBlock_instr(ctx)
            val loop = exitScope() as Loop
            currentBlock.push(loop)
        } else if (ctx.BLOCK() != null) {
            newScope(Block())
            super.visitBlock_instr(ctx)
            val block = exitScope()
            currentBlock.push(block)
        } else if (ctx.IF() != null) {
            val condition = stack.pop()
            newScope(If(condition))
            super.visitBlock(ctx.block())
            val ifMain = exitScope() as If
            if (ctx.ELSE() != null) {
                newScope(Block())
                super.visitInstr_list(ctx.instr_list())
                val falseBody = exitScope()
                ifMain.elseBody = falseBody
            }
            stack.push(ifMain)
        }
    }

    override fun visitPlain_instr(ctx: WatParser.Plain_instrContext) {
        if (ctx.UNREACHABLE() != null) {
            stack.push(Unreachable())
        } else if (ctx.BR() != null) {
            val depth = ctx.var_().first().text.toInt()
            stack.push(Br(depth))
        } else if (ctx.BR_IF() != null) {
            val depth = ctx.var_().first().text.toInt()
            val ifCondition = stack.pop()
            val target = blocks[blocks.size - depth - 1]
            val ifBody = if (target is Loop && depth == 0) {
                Continue()
            } else if (target is Block && depth == 0) {
                Break()
            } else {
                //throw Error()
                Placeholder("br_if $depth")
            }
            val brif = BrIf(ifCondition, ifBody, depth)
            stack.push(brif)
        } else if (ctx.RETURN() != null) {
            // TODO: jump to outer most block
            stack.push(Placeholder("Return"))
        } else if (ctx.CONST() != null) {
            val type = WasmValueType.parse(ctx.CONST()!!.text.substring(0, 3))
            stack.push(Value(type, ctx.literal().text))
        } else if (ctx.CALL() != null) {
            val functionIndex = ctx.var_().first().text.toInt()
            val calledFunction = module.functions.first { it.index == Index(functionIndex) }
            val (paramsTypes, resultTypes) = calledFunction.type.getParamsAndResults(module)
            val params = paramsTypes.map { stack.pop() }
            val hasReturn = resultTypes.isNotEmpty()
            stack.push(FunctionCall("f${functionIndex}", params, hasReturn))
        } else if (ctx.LOCAL_GET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function.locals[index]
            val symbol = Symbol(type,Names.local + index)
            stack.push(symbol)
        } else if (ctx.LOCAL_SET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function.locals[index]
            val symbol = Symbol(type, Names.local + index)
            val value = stack.pop()
            stack.push(Assignment(symbol, value))
        } else if (ctx.LOCAL_TEE() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function.locals[index]
            val symbol = Symbol(type, Names.local + index)
            val value = stack.pop()
            val dependant = value.symbols().any { it == symbol }
            stack.push(Assignment(symbol, value))
            if (dependant) {
                stack.push(symbol)
            } else {
                stack.push(value)
            }
        } else if (ctx.GLOBAL_GET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = module.globals[index].type.type
            val symbol = Symbol(type,Names.global + index)
            stack.push(symbol)
        } else if (ctx.GLOBAL_SET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = module.globals[index].type.type
            val symbol = Symbol( type,Names.global + index)
            stack.push(Assignment(symbol, stack.pop()))
        } else if (ctx.TEST() != null) {
            val operatorName = ctx.TEST()!!.text.substring(4)
            val expr = when (operatorName) {
                "eqz" -> BinaryOP(Operator.eq, stack.pop(), Value(WasmValueType.I32,"0"))
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
        } else if (ctx.LOAD() != null) {
            val type = WasmValueType.parse(ctx.LOAD()!!.text.substring(0, 3))
            val offset = ctx.OFFSET_EQ_NAT()?.text?.toIntOrNull() ?: 0
            val addr = stack.pop()
            val load = Load(type, addr, offset)
            stack.push(load)
        } else if (ctx.STORE() != null) {
            val type = WasmValueType.parse(ctx.STORE()!!.text.substring(0, 3))
            val offset = ctx.OFFSET_EQ_NAT()?.text?.toIntOrNull() ?: 0
            val data = stack.pop()
            val addr = stack.pop()
            val store = Store(type, data, addr, offset)
            stack.push(store)
        } else {
            throw Exception("No Case found for: " + ctx.text)
        }
        return super.visitPlain_instr(ctx)
    }

}