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
        program: Program, threadCount: Expression, generateCallKernel: (Function, Block) -> Unit,
    ): List<Block> {
        val module = program.module
        var kernels = 0
        val parallelBlocks = mutableListOf<Block>()
        program.statements.filterIsInstance<Function>().forEach { function ->
            val forBlocks = AnnotationFinder(For::class.java).apply { visit(function) { } }.result()
            for ((forLoop, replace) in forBlocks) {
                try {
                    val kernelType = module.findOraddType(listOf(WasmValueType.i32), listOf()).copy()
                    val parallelBlock = Block(annotations = forLoop.annotations.apply { removeIf { it is For } })
                    parallelBlock.parent = forLoop.parent
                    parallelBlock.indexInParent = forLoop.indexInParent
                    val parallelAnnotation = parallelBlock.annotations.filterIsInstance<Parallel>().first()


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
                        Index.next(module.functions),
                        type = kernelType,
                        locals = mutableListOf(WasmValueType.i32, WasmValueType.i32),
                    )

                    // put loop as function body
                    val kernel = Function(kernelFunction).apply {
                        val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index(0))
                        val start = Symbol(WasmScope.local, WasmValueType.i32, Index(1))
                        val end = Symbol(WasmScope.local, WasmValueType.i32, Index(2))
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
                                    size,
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
                                        Index(kernelFunction.type.params.size + kernelFunction.locals.size)
                                    )
                                    functionData.locals.add(type)
                                    return@associate Pair(private.symbol, newSymbol)
                                }
                            }
                        SymbolReplacer(toReplace).also { function.visit(it) }

                        // replace condition.right with end
                        (rangeLoop.condition as BinaryOP).right = end// what to replace with end
                        instructions.add(forLoop)
                    }
                    val kernelId = kernels++
                    val kernelAnnotation = Kernel(kernelId)
                    kernel.annotations.add(kernelAnnotation)
                    module.functions.add(kernelFunction)
                    program.statements.add(kernel)
                    rangeLoop.parent = kernel

                    // call kernel function with thread-spawn
                    // check if error code -> trap

                    var threadId = function.annotations.filterIsInstance<ThreadId>().firstOrNull()?.symbol
                    if (threadId == null) {
                        threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.next(function.functionData.locals))
                        function.functionData.locals.add(threadId.type)
                        function.annotations.add(ThreadId(threadId))
                    }

                    parallelAnnotation.threadId = threadId
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