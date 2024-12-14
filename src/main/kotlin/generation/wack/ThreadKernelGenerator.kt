package generation.wack

import analysis.dfa.DfaValue
import generation.debug.PrintLibrary
import generation.wasm.threads.MutexLibrary
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
        metaLib: MetaLibrary,
        mutex: MutexLibrary,
        print: PrintLibrary,
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

                    // replace stack_base
                    val localStackBase = forLoop.annotations.filterIsInstance<StackBase>().firstOrNull()
                    if (localStackBase != null) {
                        val replaces = mapOf<SymbolLoad, Symbol>(
                            localStackBase.symbol to Symbol.localI32(Index.number(1))
                        )
                        SymbolReplacer(replaces).also { forLoop.visit(it) }
                    }

                    // loop boundaries
                    val rangeLoop = (forLoop as RangeLoop)
                    val rangeFrom = rangeLoop.range.from
                    val rangeTo = rangeLoop.range.to
                    if (Finders.symbols(rangeFrom).isNotEmpty()) {
                        // TODO: dont ignore
                        // throw Exception("Can't Deduce Loop From Range: $rangeFrom")
                    }
                    if (Finders.symbols(rangeTo).isNotEmpty()) {
                        // RangeTo is not constant
                        // check condition

                        // TODO: dont ignore
                        //throw Exception("Can't Deduce Loop To Range: $rangeTo")
                    }
                    // from & to are constants!

                    // make function definition
                    val kernelFunction = WasmFunction(
                        Index("__kernel_$kernels"),
                        type = kernelType, /* thread_id */
                        locals = mutableListOf(
                            WasmValueType.i32/* stack_base */,
                            WasmValueType.i32/* start */,
                            WasmValueType.i32/* end */
                        ),
                    )

                    // put loop as function body
                    val kernel = Function(kernelFunction).apply {
                        val threadId = Symbol.localI32(Index.number(0))
                        val stackBase = Symbol.localI32(Index.number(1))
                        val start = Symbol.localI32(Index.number(2))
                        val end = Symbol.localI32(Index.number(3))
                        val maxThreads = metaLib.maxThreads.get.call().result

                        // TODO: Only if has stack_base
                        // new stack_base
                        instructions.add(
                            Assignment(
                                stackBase,
                                metaLib.stackBase.get.call().result,
                            )
                        )

                        // size = to - from
                        val size = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.sub,
                            rangeLoop.conditionBinaryOP.endExclusive,
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
                                        maxThreads,
                                    ),
                                    threadId,
                                )
                            )
                        )

                        // calculate end
                        // int end = (thread_num == num_threads - 1) ? end : (size / num_threads) * (thread_num + 1);
                        instructions.add(
                            Assignment(
                                end, Select(
                                    rangeLoop.conditionBinaryOP.right,
                                    BinaryOP(
                                        WasmValueType.i32,
                                        BinaryOP.Operator.mul,
                                        BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.div.copy(signed = WasmBitSign.s),
                                            size,
                                            maxThreads,
                                        ),
                                        BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.add,
                                            threadId,
                                            Value.one,
                                        ),
                                    ),
                                    BinaryOP(
                                        WasmValueType.i32, BinaryOP.Operator.eq, threadId, BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.sub,
                                            maxThreads,
                                            Value.one
                                        )
                                    ),
                                    WasmValueType.i32,
                                )
                            )
                        )

                        // debug print range
                        /*instructions.addAll(
                            mutex.criticalSection { print.print(start, end) }
                        )*/
                        //instructions.addAll(mutex.criticalSection { print.print(stackBase) })

                        forLoop.range = DfaValue.Range(start, end)
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
                        SymbolReplacer(toReplace).also { forLoop.visit(it) }

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
                        forLoop.annotations.filterIsInstance<StackBase>().firstOrNull()?.let {
                            parallelBlock.annotations.add(it)
                        }
                        generateCallKernel(function, this)
                    }
                    replace(parallelBlock)
                    parallelBlocks.add(parallelBlock)
                } catch (e: Exception) {
                    error(e)
                }
            }
        }
        return parallelBlocks
    }

}