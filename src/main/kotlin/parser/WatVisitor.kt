package parser

import ir.expression.*
import ir.finder.Finders
import ir.statement.*
import org.antlr.v4.runtime.tree.ParseTree
import wasm.*
import java.lang.Exception

class WatVisitor(val module: WasmModule) : WatParserBaseVisitor<Unit>() {

    private var function: WasmFunction? = null
    private val blocks = mutableListOf<Block>()

    private val currentBlock: Block
        get() = blocks.last()

    val stack: Block
        get() = currentBlock

    private fun newScope(block: Block) {
        blocks.add(block)
    }

    private fun exitScope(): Block {
        val block = blocks.removeLast()
        return block
    }

    fun visitFunction(function: WasmFunction): List<Statement> {
        this.function = function
        blocks.add(
            Block(
                hasReturn = function.type.result.isNotEmpty(),
                brackets = false
            )
        )
        visit(function.code)
        this.function = null
        val result = stack.instructions
        blocks.clear()
        return result
    }

    fun visitArbitrary(tree: ParseTree): List<Statement> {
        blocks.add(Block())
        visit(tree)
        val result = stack.instructions
        blocks.clear()
        return result
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
                ifMain.elseBody = falseBody.instructions
            }
            stack.push(ifMain)
        }
    }

    override fun visitPlain_instr(ctx: WatParser.Plain_instrContext) {
        if (ctx.UNREACHABLE() != null) {
            stack.push(Unreachable())
        } else if (ctx.BR() != null) {
            val depth = ctx.var_().first().text.toInt()
            val target = blocks[blocks.size - depth - 1]
            stack.push(Br(target, depth))
        } else if (ctx.BR_IF() != null) {
            val depth = ctx.var_().first().text.toInt()
            val ifCondition = stack.pop()
            val target = blocks[blocks.size - depth - 1]
            val brif = BrIf(ifCondition, target, depth)
            stack.push(brif)
        } else if (ctx.RETURN() != null) {
            stack.push(Return())
        } else if (ctx.CONST() != null) {
            val type = WasmValueType.parse(ctx.CONST()!!.text.substring(0, 3))
            stack.push(Value(type, ctx.literal().text))
        } else if (ctx.CALL() != null) {
            val functionIndex = Index(ctx.var_().first().text.toInt())
            val calledFunction = module.functions.first { it.index == functionIndex }
            val (paramsTypes, resultTypes) = calledFunction.type.getParamsAndResults(module)
            val params = paramsTypes.map { stack.pop() }
            val hasReturn = resultTypes.isNotEmpty()
            stack.push(FunctionCall(functionIndex, params, hasReturn))
        } else if (ctx.LOCAL_GET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function!!.locals[index]
            val symbol = Symbol(WasmScope.local, type, Index(index))
            stack.push(symbol)
        } else if (ctx.LOCAL_SET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function!!.locals[index]
            val symbol = Symbol(WasmScope.local, type, Index(index))
            val value = stack.pop()
            stack.push(Assignment(symbol, value))
        } else if (ctx.LOCAL_TEE() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function!!.locals[index]
            val symbol = Symbol(WasmScope.local, type, Index(index))
            val value = stack.pop()
            val dependant = Finders.symbols(value).any { it == symbol }
            stack.push(Assignment(symbol, value, tee = true))
            if (dependant) {
                stack.push(TeeSymbol(symbol))
            } else {
                stack.push(TeeValue(value))
            }
        } else if (ctx.GLOBAL_GET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = module.globals[index].type.type
            val symbol = Symbol(WasmScope.global, type, Index(index))
            stack.push(symbol)
        } else if (ctx.GLOBAL_SET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = module.globals[index].type.type
            val symbol = Symbol(WasmScope.global, type, Index(index))
            stack.push(Assignment(symbol, stack.pop()))
        } else if (ctx.TEST() != null) {
            val (typeString, operatorName) = ctx.TEST()!!.text.split(".")
            val type = WasmValueType.parse(typeString)
            val expr = when (operatorName) {
                "eqz" -> BinaryOP(type, Operator.eq, stack.pop(), Value(WasmValueType.i32, "0"))
                else -> throw Error()
            }
            stack.push(expr)
        } else if (ctx.COMPARE() != null) {
            val (typeString, operatorName) = ctx.COMPARE()!!.text.split(".")
            val type = WasmValueType.parse(typeString)
            val operatorSign = when (operatorName) {
                "eq" -> Operator.eq
                "ne" -> Operator.neq
                "lt", "lt_s", "lt_u" -> Operator.lt
                "le", "le_s", "le_u" -> Operator.le
                "gt", "gt_s", "gt_u" -> Operator.gt
                "ge", "ge_s", "ge_u" -> Operator.ge
                else -> throw Error()
            }
            val signed = if (operatorName.length > 2) BitSign.valueOf(operatorName.last().toString()) else null
            val operator = Operator(operatorSign.sign, operatorSign.watName, signed)
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(type, operator, first, second))
        } else if (ctx.BINARY() != null) {
            val (typeString, operatorName) = ctx.BINARY()!!.text.split(".")
            val type = WasmValueType.parse(typeString)
            val operatorSign = when (operatorName) {
                "add" -> Operator.add
                "sub" -> Operator.sub
                "and" -> Operator.and
                "shl" -> Operator.shl
                else -> throw Error()
            }
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(type, operatorSign, first, second))
        } else if (ctx.LOAD() != null) {
            val type = WasmValueType.parse(ctx.LOAD()!!.text.substring(0, 3))
            val offset = ctx.OFFSET_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val align = ctx.ALIGN_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val addr = stack.pop()
            val load = Load(type, addr, offset, align)
            stack.push(load)
        } else if (ctx.STORE() != null) {
            val type = WasmValueType.parse(ctx.STORE()!!.text.substring(0, 3))
            val offset = ctx.OFFSET_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val align = ctx.ALIGN_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val data = stack.pop()
            val addr = stack.pop()
            val store = Store(Load(type, addr, offset, align), data)
            stack.push(store)
        } else {
            throw Exception("No Case found for: " + ctx.text)
        }
        return super.visitPlain_instr(ctx)
    }

}