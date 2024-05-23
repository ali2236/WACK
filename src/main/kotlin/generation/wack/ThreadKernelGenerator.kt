package generation.wack

import ir.annotations.For
import ir.annotations.Kernel
import ir.annotations.ThreadId
import ir.annotations.WackAnnotation
import ir.expression.*
import ir.finder.AnnotationFinder
import ir.finder.Finders
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import wasm.*

object ThreadKernelGenerator {
    fun generate(program: Program, threadCount: WasmGlobal, threadSpawn: WasmFunction, arg: ThreadArg) {
        val module = program.module
        var kernels = 0
        program.statements.filterIsInstance<Function>().forEach { function ->
            val forBlocks = AnnotationFinder(For::class.java).apply { visit(function) { } }.result()
            for ((forLoop, replace) in forBlocks) {
                try {
                    val kernelType = module.findOraddType(listOf(WasmValueType.i32), listOf()).copy()
                    val parallelBlock = Block(annotations = forLoop.annotations.apply { removeIf { it is For } })

                    // find function locals used
                    val localsAccessed = Finders.symbols(forLoop.instructions).toSet()


                    // loop boundaries
                    // TODO: determine value for local using dfa+eval+ssa
                    val rangeLoop = (forLoop as RangeLoop)
                    val rangeFrom = rangeLoop.from()
                    val rangeTo = rangeLoop.to()
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
                            Operator.sub,
                            rangeTo,
                            rangeFrom,
                        )

                        // calculate start
                        // int start = (size / num_threads) * thread_num;
                        instructions.add(
                            Assignment(
                                start, BinaryOP(
                                    WasmValueType.i32,
                                    Operator.mul,
                                    BinaryOP(
                                        WasmValueType.i32,
                                        Operator.div,
                                        size,
                                        threadCount.symbol,
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
                                        Operator.mul,
                                        BinaryOP(
                                            WasmValueType.i32,
                                            Operator.div,
                                            size,
                                            threadCount.symbol,
                                        ),
                                        BinaryOP(
                                            WasmValueType.i32,
                                            Operator.add,
                                            threadId,
                                            Value(WasmValueType.i32, "1"),
                                        ),
                                    ),
                                    size,
                                    BinaryOP(
                                        WasmValueType.i32, Operator.eq, threadId, BinaryOP(
                                            WasmValueType.i32,
                                            Operator.sub,
                                            threadCount.symbol,
                                            Value(WasmValueType.i32, "1")
                                        )
                                    ),
                                    WasmValueType.i32,
                                )
                            )
                        )

                        // replace locals with new boundaries
                        // replace init with start
                        rangeLoop.init.replaceAssign(start) // what to replace with start
                        // replace condition.right with end
                        (rangeLoop.condition as BinaryOP).right = end// what to replace with end
                        instructions.add(forLoop)
                    }
                    val kernelId = kernels++
                    val kernelAnnotation = Kernel(kernelId)
                    kernel.annotations.add(kernelAnnotation)
                    module.functions.add(kernelFunction)
                    program.statements.add(kernel)

                    // replace locals with new locals
                    ReplaceableFinder(Symbol::class.java).also { it.visit(kernel) {} }.result().forEach {
                        val smbl = it.statement
                        val newIndex = localsAccessed.indexOf(smbl)
                        val newSmbl = Symbol(smbl.scope, smbl.type, Index(newIndex))
                        it.replace(newSmbl)
                    }

                    // call kernel function with thread-spawn
                    // check if error code -> trap

                    val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.next(function.functionData.locals))
                    function.functionData.locals.add(threadId.type)
                    function.annotations.add(ThreadId(threadId))

                    parallelBlock.apply {
                        instructions.add(
                            If(
                                BinaryOP(
                                    WasmValueType.i32,
                                    Operator.lt,
                                    threadSpawn.call(arg.encode.call(threadId, Value(WasmValueType.i32, "$kernelId"))),
                                    Value(WasmValueType.i32, "0")
                                ),
                                mutableListOf(Unreachable())
                            ),
                        )
                    }
                    replace(parallelBlock)
                } catch (e: Exception) {

                }
            }
        }
    }

}