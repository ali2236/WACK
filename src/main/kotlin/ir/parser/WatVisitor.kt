package ir.parser

import ir.expression.*
import ir.statement.*
import ir.wasm.*
import org.antlr.v4.runtime.tree.ParseTree
import parser.WatParser
import parser.WatParserBaseVisitor
import kotlin.Exception

class WatVisitor(val module: WasmModule) : WatParserBaseVisitor<Unit>() {

    private var function: WasmFunction? = null
    private val blocks = mutableListOf<Block>()

    private val parentBlock: Block
        get() = blocks[blocks.size - 2]
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
        var type: WasmValueType? = null
        if (ctx.block().block_type() != null) {
            type = WasmValueType.parse(ctx.block().block_type().value_type().text)
        }
        if (ctx.LOOP() != null) {
            newScope(Loop())
            super.visitBlock_instr(ctx)
            val loop = exitScope() as Loop
            currentBlock.push(loop)
            if (type != null) {
                currentBlock.push(BlockResult(type, loop))
            }
        } else if (ctx.BLOCK() != null) {
            newScope(Block(type = type))
            super.visitBlock_instr(ctx)
            val block = exitScope()
            currentBlock.push(block)
            if (type != null) {
                currentBlock.push(BlockResult(type, block))
            }
        } else if (ctx.IF() != null) {
            val condition = stack.pop()
            newScope(If(condition, type = type))
            super.visitBlock(ctx.block())
            val ifMain = exitScope() as If
            if (ctx.ELSE() != null) {
                newScope(Block())
                super.visitInstr_list(ctx.instr_list())
                val falseBody = exitScope()
                ifMain.elseBody = falseBody.instructions
            }
            stack.push(ifMain)
            if (type != null) {
                currentBlock.push(BlockResult(type, ifMain))
            }
        }
    }

    override fun visitPlain_instr(ctx: WatParser.Plain_instrContext) {
        if (ctx.UNREACHABLE() != null) {
            stack.push(Unreachable())
        } else if (ctx.BR() != null) {
            val depth = ctx.var_().first().text.toInt()
            val target = blocks[blocks.size - depth - 1]
            val result = if (target.type != null) stack.pop() else null
            stack.push(Br(target, depth, result))
        } else if (ctx.BR_IF() != null) {
            val depth = ctx.var_().first().text.toInt()
            val ifCondition = stack.pop()
            val target = blocks[blocks.size - depth - 1]
            val result = if (target.type != null) stack.pop() else null
            val brif = BrIf(ifCondition, target, depth, result)
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
            stack.push(FunctionCall(functionIndex, params, resultTypes))
        } else if(ctx.CALL_INDIRECT() != null){
            val tableIndex = Index(ctx.var_().firstOrNull()?.text?.toIntOrNull() ?: 0)
            val typeIndex = Index(ctx.type_use().var_().text?.toIntOrNull() ?: 0)
            val type = module.functionTypes.first { it.index == typeIndex }
            val functionIndex = stack.pop()
            val params = mutableListOf<Expression>()
            type.params.forEach {
                params.add(stack.pop())
            }

            stack.push(IndirectFunctionCall(tableIndex, typeIndex, functionIndex, params, type.result))
        } else if (ctx.LOCAL_GET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function!!.allLocals[index]
            val symbol = Symbol(WasmScope.local, type, Index(index))
            stack.push(symbol)
        } else if (ctx.LOCAL_SET() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function!!.allLocals[index]
            val symbol = Symbol(WasmScope.local, type, Index(index))
            val value = stack.pop()
            stack.push(Assignment(symbol, value))
        } else if (ctx.LOCAL_TEE() != null) {
            val index = ctx.var_().first().text.toInt()
            val type = function!!.allLocals[index]
            val symbol = Symbol(WasmScope.local, type, Index(index))
            val value = stack.pop()
            stack.push(Assignment(symbol, value, tee = true))
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
                "eqz" -> BinaryOP(type, BinaryOP.Operator.eq, stack.pop(), Value.zero)
                else -> throw Error()
            }
            stack.push(expr)
        } else if (ctx.COMPARE() != null) {
            val (typeString, operatorName) = ctx.COMPARE()!!.text.split(".")
            val type = WasmValueType.parse(typeString)
            val operatorSign = when (operatorName) {
                "eq" -> BinaryOP.Operator.eq
                "ne" -> BinaryOP.Operator.neq
                "lt", "lt_s", "lt_u" -> BinaryOP.Operator.lt
                "le", "le_s", "le_u" -> BinaryOP.Operator.le
                "gt", "gt_s", "gt_u" -> BinaryOP.Operator.gt
                "ge", "ge_s", "ge_u" -> BinaryOP.Operator.ge
                else -> throw Error()
            }
            val signed = if (operatorName.length > 2) WasmBitSign.valueOf(operatorName.last().toString()) else null
            val operator = BinaryOP.Operator(operatorSign.sign, operatorSign.watName, signed)
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(type, operator, first, second))
        } else if (ctx.BINARY() != null) {
            val (typeString, operatorName) = ctx.BINARY()!!.text.split(".")
            val type = WasmValueType.parse(typeString)
            val operatorSign = when (operatorName) {
                "add" -> BinaryOP.Operator.add
                "sub" -> BinaryOP.Operator.sub
                "and" -> BinaryOP.Operator.and
                "shl" -> BinaryOP.Operator.shl
                "mul" -> BinaryOP.Operator.mul
                "or" -> BinaryOP.Operator.or
                "shr_u" -> BinaryOP.Operator.shr.copy(signed = WasmBitSign.u)
                "shr_s" -> BinaryOP.Operator.shr.copy(signed = WasmBitSign.s)
                "xor" -> BinaryOP.Operator.xor
                "div_u" -> BinaryOP.Operator.div.copy(signed = WasmBitSign.u)
                "div_s" -> BinaryOP.Operator.div.copy(signed = WasmBitSign.s)
                "rem_u" -> BinaryOP.Operator.rem.copy(signed = WasmBitSign.u)
                "rem_s" -> BinaryOP.Operator.rem.copy(signed = WasmBitSign.s)
                else -> throw Error("unkown binary operator $operatorName")
            }
            val second = stack.pop()
            val first = stack.pop()
            stack.push(BinaryOP(type, operatorSign, first, second))
        } else if (ctx.UNARY() != null) {
            val (typeString, operatorName) = ctx.UNARY()!!.text.split(".")
            val type = WasmValueType.parse(typeString)
            val operatorSign = when (operatorName) {
                "neg" -> UnaryOP.Operator.neg
                "abs" -> UnaryOP.Operator.abs
                else -> throw Error("unknown unary operator $operatorName")
            }
            stack.push(UnaryOP(type, operatorSign, stack.pop()))
        } else if (ctx.LOAD() != null) {
            val type = WasmValueType.parse(ctx.LOAD()!!.text.substring(0, 3))
            val offset = ctx.OFFSET_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val align = ctx.ALIGN_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val addr = stack.pop()
            val memoryIndex = Index(ctx.var_().firstOrNull()?.text?.toIntOrNull() ?: 0)
            val _meta = ctx.LOAD()!!.text.split("load").last()
            var memSize: Int? = null
            var memSign: WasmBitSign? = null
            if (_meta.isNotEmpty()) {
                val (_size, _sign) = _meta.split("_")
                memSize = _size.toInt()
                memSign = WasmBitSign.valueOf(_sign)
            }
            val load = Load(type, addr, memoryIndex, offset, align, memSize, memSign)
            stack.push(load)
        } else if (ctx.STORE() != null) {
            val type = WasmValueType.parse(ctx.STORE()!!.text.substring(0, 3))
            val offset = ctx.OFFSET_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val align = ctx.ALIGN_EQ_NAT()?.text?.substringAfter("=")?.toIntOrNull() ?: 0
            val data = stack.pop()
            val addr = stack.pop()
            val memoryIndex = Index(ctx.var_().firstOrNull()?.text?.toIntOrNull() ?: 0)
            val _meta = ctx.STORE()!!.text.split("store").last()
            var memSize: Int? = null
            if (_meta.isNotEmpty()) {
                memSize = _meta.toInt()
            }
            val store = Store(Load(type, addr, memoryIndex, offset, align, memSize), data)
            stack.push(store)
        } else if (ctx.NOP() != null) {
            stack.push(Nop())
        } else if (ctx.DROP() != null) {
            try {
                val value = stack.pop()
                val drop = Drop(value)
                stack.push(drop)
            } catch (e: Exception){
                stack.push(RawWat(ctx.text))
            }
        } else if (ctx.CONVERT() != null) {
            val (typeRaw, instruction) = ctx.CONVERT()!!.text.split(".")
            val type = WasmValueType.parse(typeRaw)
            stack.push(Convert(type, instruction, stack.pop()))
        } else if (ctx.SELECT() != null) {
            val selector = stack.pop()
            val val2 = stack.pop()
            val val1 = stack.pop()
            val type = if (ctx.select_type() != null) {
                WasmValueType.parse(ctx.select_type().value_type().text)
            } else {
                null
            }
            stack.push(Select(val1, val2, selector, type))
        } else if (ctx.BR_TABLE() != null) {
            val jumps = ctx.var_().map { it.text.toInt() }
            stack.push(BrTable(stack.pop(), jumps))
        } else if (ctx.MEMORY_SIZE() != null) {
            val memoryIndex = Index(ctx.var_()?.firstOrNull()?.text?.toInt() ?: 0)
            stack.push(MemorySize(memoryIndex))
        } else if (ctx.MEMORY_GROW() != null) {
            val memoryIndex = Index(ctx.var_()?.firstOrNull()?.text?.toInt() ?: 0)
            stack.push(MemoryGrow(memoryIndex, stack.pop()))
        } else if (ctx.MEMORY_COPY() != null) {
            val z = Index(0)
            var (from, to) = Pair(z, z)
            if (ctx.var_()?.size == 2) {
                ctx.var_()!!.map { Index(it.text.toInt()) }.also {
                    from = it.first()
                    to = it.last()
                }
            }
            val n = stack.pop()
            val i2 = stack.pop()
            val i1 = stack.pop()
            stack.push(MemoryCopy(from, to, n, i1, i2))
        } else if (ctx.MEMORY_FILL() != null) {
            val memoryIndex = Index(ctx.var_()?.firstOrNull()?.text?.toInt() ?: 0)
            val n = stack.pop()
            val value = stack.pop()
            val i = stack.pop()
            stack.push(MemoryFill(memoryIndex, i, value, n))
        } else {
            throw Exception("No Case found for: " + ctx.text)
        }
        return super.visitPlain_instr(ctx)
    }

}