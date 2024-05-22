package generation.wack

import ir.annotations.For
import ir.expression.BinaryOP
import ir.expression.FunctionCall
import ir.expression.Operator
import ir.expression.Symbol
import ir.finder.AnnotationFinder
import ir.finder.Finders
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import wasm.*

object ThreadKernelGenerator {
    fun generate(program: Program, threadCount: WasmGlobal) {
        val module = program.module
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
                    val kernelFunction = WasmFunction(Index.next(module.functions), type = kernelType)
                    kernelFunction.locals.addAll(listOf(WasmValueType.i32, WasmValueType.i32))

                    // put loop as function body
                    val kernel = Function(kernelFunction).apply {
                        val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index(0))
                        val start = Symbol(WasmScope.local, WasmValueType.i32, Index(1))
                        val end = Symbol(WasmScope.local, WasmValueType.i32, Index(2))

                        // calculate start
                        // int start = ((to - from) / num_threads) * thread_num;
                        instructions.add(
                            Assignment(
                                start, BinaryOP(
                                    WasmValueType.i32,
                                    Operator.mul,
                                    BinaryOP(
                                        WasmValueType.i32,
                                        Operator.div,
                                        BinaryOP(
                                            WasmValueType.i32,
                                            Operator.sub,
                                            rangeTo,
                                            rangeFrom,
                                        ),
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
                                end,
                                // TODO: select
                            )
                        )

                        instructions.add(forLoop)
                    }

                    // replace locals with new locals
                    ReplaceableFinder(Symbol::class.java).also { it.visit(kernel) {} }.result().forEach {
                        val smbl = it.statement
                        val newIndex = localsAccessed.indexOf(smbl)
                        val newSmbl = Symbol(smbl.scope, smbl.type, Index(newIndex))
                        it.replace(newSmbl)
                    }

                    // call kernel function with each thread portion
                    parallelBlock.push(FunctionCall(kernelFunction.index, localsAccessed.toList(), false))

                    // commit change
                    replace(parallelBlock)
                    module.functions.add(kernelFunction)
                    program.statements.add(kernel)
                } catch (e: Exception) {

                }
            }
        }
    }

}