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
                            WasmValueType.i32/* start */,
                            WasmValueType.i32/* end */
                        ),
                    )

                    // put loop as function body
                    val kernel = Function(kernelFunction).apply {
                        val taskId = Symbol.localI32(Index.number(0))
                        val start = Symbol.localI32(Index.number(1))
                        val end = Symbol.localI32(Index.number(2))
                        val tasksCount = metaLib.tasksCount.get.call().result

                        // create transfer variables
                        val localTransferIn = forLoop.annotations.filterIsInstance<TransferIn>()
                        val replaceMap = mutableMapOf<SymbolLoad, Symbol>()
                        val loadTransferee = mutableListOf<Statement>()
                        localTransferIn.forEach { t ->
                            // allocate new symbol for transfere
                            kernelFunction.locals.add(t.symbol.type)
                            val newSymbol = Symbol(WasmScope.local, t.symbol.type, Index.next(kernelFunction.locals))
                            replaceMap[t.symbol] = newSymbol
                            loadTransferee.add(
                                Assignment(
                                    newSymbol,
                                    metaLib.localTransfer(t.symbol.type).load.call(Value.i32(t.index)).result,
                                )
                            )

                            /*loadTransferee.addAll(
                                mutex.criticalSection { print.print(threadId, newSymbol) }
                            )*/
                        }

                        // replace old references
                        SymbolReplacer(replaceMap).also { forLoop.visit(it) }

                        // get transferees values
                        instructions.addAll(loadTransferee)

                        // size = to - from
                        val size = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.sub,
                            rangeLoop.conditionBinaryOP.endExclusive,
                            rangeFrom,
                        )

                        // calculate start
                        // int start = (size / num_tasks) * task_id;
                        instructions.add(
                            Assignment(
                                start, BinaryOP(
                                    WasmValueType.i32,
                                    BinaryOP.Operator.mul,
                                    BinaryOP(
                                        WasmValueType.i32,
                                        BinaryOP.Operator.div.copy(signed = WasmBitSign.s),
                                        size,
                                        tasksCount,
                                    ),
                                    taskId,
                                )
                            )
                        )

                        // calculate end
                        // int end = (task_id == tasks_count - 1) ? end : (size / tasks_count) * (task_id + 1);
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
                                            tasksCount,
                                        ),
                                        BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.add,
                                            taskId,
                                            Value.one,
                                        ),
                                    ),
                                    BinaryOP(
                                        WasmValueType.i32, BinaryOP.Operator.eq, taskId, BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.sub,
                                            tasksCount,
                                            Value.one
                                        )
                                    ),
                                    WasmValueType.i32,
                                )
                            )
                        )


                        ///
                        /// Loop Body
                        ///
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

                        ///
                        /// Reductions
                        ///
                        /// mutex_lock {
                        ///     <reduction_var> = <reduction_var> <op> <local_reduction_var>
                        /// }
                        if (forLoop.hasAnnotation(Reduction::class.java)) {
                            val reductions = forLoop.annotations.filterIsInstance<Reduction>()
                            for (reduction in reductions) {
                                val symbol = reduction.symbol
                                val localSymbol = toReplace[symbol] ?: continue
                                instructions.addAll(mutex.criticalSection {
                                    when (symbol) {
                                        is Load -> {
                                            Store(
                                                symbol,
                                                BinaryOP(symbol.type, reduction.operator, symbol, localSymbol)
                                            )
                                        }

                                        else -> {
                                            throw Exception("Symbol reductions are not yet supported")
                                            //Assignment()
                                        }
                                    }
                                })
                            }
                        }

                        ///
                        /// TransferOut
                        ///
                        /// if(task_id == tasks_count - 1){
                        ///     save_local_<type>(index, value)
                        /// }
                        if (forLoop.hasAnnotation(TransferOut::class.java)) {
                            val transfers = forLoop.annotations.filterIsInstance<TransferOut>()
                            instructions.add(
                                If(
                                    BinaryOP(
                                        WasmValueType.i32,
                                        BinaryOP.Operator.eq,
                                        taskId,
                                        BinaryOP.minus(tasksCount, Value.one),
                                    ),
                                    trueBody = transfers.map { transfer ->
                                        val symbol = transfer.symbol
                                        val index = Value.i32(transfer.index)
                                        metaLib.localTransfer(symbol.type).save.call(symbol, index)
                                    }.toMutableList()
                                ),
                            )
                        }

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
                        forLoop.annotations.filter { it is TransferIn || it is TransferOut || it is Tasks }.forEach {
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