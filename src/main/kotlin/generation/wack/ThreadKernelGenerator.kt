package generation.wack

import ir.annotations.*
import ir.expression.*
import ir.finder.AnnotationFinder
import ir.finder.Finders
import ir.finder.SymbolReplacer
import ir.statement.*
import ir.statement.Function
import ir.wasm.*

object ThreadKernelGenerator {
    fun generate(
        program: Program,
        threadCount: Expression,
        generateCallKernel: (Function, Block) -> Unit,
    ): List<Block> {
        val module = program.module
        var kernels = 0
        val parallelBlocks = mutableListOf<Block>()
        program.statements.filterIsInstance<Function>().forEach { function ->
            val forBlocks = AnnotationFinder(For::class.java).apply { visit(function) { } }.result()
            for ((forLoop, replace) in forBlocks) {
                try {
                    val kernelType = module.findOrAddType(listOf(WasmValueType.i32), listOf()).copy()
                    val parallelBlock = Block()
                    parallelBlock.parent = forLoop.parent
                    parallelBlock.indexInParent = forLoop.indexInParent

                    // loop boundaries
                    val rangeLoop = (forLoop as RangeLoop)
                    val rangeFrom = rangeLoop.range.from
                    val rangeTo = rangeLoop.range.to
                    if (Finders.symbols(rangeFrom).isNotEmpty()) {
                        throw Exception("Can't Deduce Loop From Range: $rangeFrom")
                    }
                    if (Finders.symbols(rangeTo).isNotEmpty()) {
                        throw Exception("Can't Deduce Loop To Range: $rangeTo")
                    }
                    // from & to are constants!

                    // make function definition
                    val kernelFunction = WasmFunction(
                        Index("__kernel_$kernels"),
                        type = kernelType,
                        locals = mutableListOf(WasmValueType.i32, WasmValueType.i32),
                    )

                    // put loop as function body
                    val kernel = Function(kernelFunction).apply {
                        val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(0))
                        val start = Symbol(WasmScope.local, WasmValueType.i32, Index.number(1))
                        val end = Symbol(WasmScope.local, WasmValueType.i32, Index.number(2))
                        // size = to - from
                        val size = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.sub,
                            rangeTo,
                            rangeFrom,
                        )

                        // calculate start
                        // int start = (size / num_threads) * thread_num;
                        instructions.add(
                            Assignment(
                                start, BinaryOP(
                                    WasmValueType.i32,
                                    BinaryOP.Operator.mul,
                                    BinaryOP(
                                        WasmValueType.i32,
                                        BinaryOP.Operator.div.copy(signed = WasmBitSign.s),
                                        size,
                                        threadCount,
                                    ),
                                    threadId,
                                )
                            )
                        )

                        // calculate end
                        // int end = (thread_num == num_threads - 1) ? size : (size / num_threads) * (thread_num + 1);
                        instructions.add(
                            Assignment(
                                end, Select(
                                    size,
                                    BinaryOP(
                                        WasmValueType.i32,
                                        BinaryOP.Operator.mul,
                                        BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.div.copy(signed = WasmBitSign.s),
                                            size,
                                            threadCount,
                                        ),
                                        BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.add,
                                            threadId,
                                            Value(WasmValueType.i32, "1"),
                                        ),
                                    ),
                                    BinaryOP(
                                        WasmValueType.i32, BinaryOP.Operator.eq, threadId, BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.sub,
                                            threadCount,
                                            Value(WasmValueType.i32, "1")
                                        )
                                    ),
                                    WasmValueType.i32,
                                )
                            )
                        )

                        /*val print = module.exports.first { it.name == "\"print_i32\"" }.index
                        val debug = arrayOf(
                            // lock mutex
                            threadCount,
                            RawWat("i32.const 4"),
                            RawWat("i32.mul"),
                            mutex!!.lock.call(),
                            // debug
                            RawWat("local.get 1"),
                            RawWat("local.get 2"),
                            RawWat("call \$f$print"),
                            // debug end
                            // unlock mutex
                            threadCount,
                            RawWat("i32.const 4"),
                            RawWat("i32.mul"),
                            mutex.unlock.call(),
                        )
                        instructions.addAll(debug)*/

                        instructions.add(forLoop)

                        // replace locals with new boundaries
                        // replace init with start
                        // there are n loops that may have stack allocated ranges
                        val toReplace = rangeLoop.annotations
                            .filterIsInstance<Private>()
                            .associate { private ->
                                if (private.symbol == rangeLoop.symbol) {
                                    return@associate Pair(private.symbol, start)
                                } else {
                                    val type = (private.symbol as Expression).exprType()
                                    val newSymbol = Symbol(
                                        WasmScope.local,
                                        type,
                                        Index.number(kernelFunction.type.params.size + kernelFunction.locals.size)
                                    )
                                    functionData.locals.add(type)
                                    return@associate Pair(private.symbol, newSymbol)
                                }
                            }
                        SymbolReplacer(toReplace).also { this.visit(it) }

                        // replace condition.right with end
                        (rangeLoop.condition as BinaryOP).right = end// what to replace with end
                    }
                    val kernelId = kernels++
                    val kernelAnnotation = Kernel(kernelId)
                    kernel.annotations.add(kernelAnnotation)
                    module.functions.add(kernelFunction)
                    program.statements.add(kernel)
                    rangeLoop.parent = kernel

                    // call kernel function with thread-spawn
                    // check if error code -> trap

                    parallelBlock.apply {
                        annotations.add(CallKernel(kernelId))
                        generateCallKernel(function, this)
                    }
                    replace(parallelBlock)
                    parallelBlocks.add(parallelBlock)
                } catch (e: Exception) {

                }
            }
        }
        return parallelBlocks
    }

}