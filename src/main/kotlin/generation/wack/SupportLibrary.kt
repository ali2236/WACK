package generation.wack

import compiler.WAPC
import generation.debug.PrintLibrary
import generation.wasi.threads.WasiThreadsLibrary
import generation.wasm.threads.MutexLibrary
import ir.expression.BinaryOP
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.*
import ir.wasm.*

class SupportLibrary(
    val parallel: WasmFunction
) {
    companion object {
        fun generate(
            program: Program,
            mutex: MutexLibrary,
            meta: MetaLibrary,
            wackThread: WackThread,
            wasiThreads: WasiThreadsLibrary,
            print: PrintLibrary,
        ): SupportLibrary {

            // make_thread_pool
            val makeThreadPoolMaxThreads = Symbol.localI32(Index.number(0))
            val makeThreadPoolTid = Symbol.localI32(Index.number(1))
            val makeThreadPool = program.addFunction(
                name = "make_thread_pool",
                params = listOf(WasmValueType.i32),
                locals = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    // make sure thread_pool does not already exist
                    If(
                        condition = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.eq,
                            meta.threadPoolState.get.call().result,
                            Value.one,
                        ),
                        // if exists do nothing
                        trueBody = mutableListOf(Return())
                    ),
                    //*mutex.criticalSection { print.print(Value.i32(500)) },
                    // set_max_threads(max_threads);
                    meta.maxThreads.set.call(makeThreadPoolMaxThreads),
                    // for(int i=0;i<max_threads;i++)
                    Loop(
                        mutableListOf(
                            If(
                                condition = BinaryOP(
                                    WasmValueType.i32,
                                    BinaryOP.Operator.lt.copy(signed = WasmBitSign.s),
                                    makeThreadPoolTid,
                                    makeThreadPoolMaxThreads,
                                ),
                                trueBody = mutableListOf(
                                    // set initial mutex values
                                    wackThread.setMutex1(makeThreadPoolTid, Value.zero),
                                    wackThread.setMutex2(makeThreadPoolTid, Value.one),
                                    // if(thread_spawn(i) < 0) exit(1)
                                    If(
                                        condition = BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.lt.copy(signed = WasmBitSign.s),
                                            wasiThreads.spawnThread.call(makeThreadPoolTid).result,
                                            Value.zero,
                                        ),
                                        trueBody = mutableListOf(Unreachable()),
                                    ),
                                    // i++;
                                    Assignment(makeThreadPoolTid, BinaryOP.increment(makeThreadPoolTid)),
                                    // continue;
                                    RawWat("br 1"),
                                ),
                            )
                        ),
                    ),
                    // set_thread_pool_state(1);
                    meta.threadPoolState.set.call(Value.one),
                ),
            )

            val kernelId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(0))
            val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(1))
            val threadCount = Symbol(WasmScope.local, WasmValueType.i32, Index.number(2))
            val parallel = program.addFunction(
                name = "wack__parallel",
                params = listOf(WasmValueType.i32),
                locals = listOf(WasmValueType.i32, WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    //*mutex.criticalSection { print.print(kernelId) },
                    // if max_threads is not assigned, use compiler default
                    Assignment(threadCount, meta.nonZeroMaxThreads.call().result),
                    makeThreadPool.functionData.call(threadCount),
                    meta.kernelId.set.call(kernelId),
                    Assignment(threadId, Value.zero),
                    Loop(
                        instructions = mutableListOf(
                            If(
                                condition = BinaryOP(
                                    WasmValueType.i32,
                                    BinaryOP.Operator.lt.copy(signed = WasmBitSign.s),
                                    threadId,
                                    threadCount,
                                ),
                                trueBody = mutableListOf(
                                    //print.print(threadId),
                                    mutex.lock.call(wackThread.getMutex1(threadId)),
                                    mutex.unlock.call(wackThread.getMutex2(threadId)),
                                    Assignment(threadId, BinaryOP.increment(threadId)),
                                    RawWat("br 1"),
                                ),
                            ),
                        ),
                    ),
                    Assignment(threadId, Value.zero),
                    Loop(
                        instructions = mutableListOf(
                            If(
                                condition = BinaryOP(
                                    WasmValueType.i32,
                                    BinaryOP.Operator.lt.copy(signed = WasmBitSign.s),
                                    threadId,
                                    threadCount,
                                ),
                                trueBody = mutableListOf(
                                    mutex.join.call(wackThread.getMutex1(threadId)),
                                    Assignment(threadId, BinaryOP.increment(threadId)),
                                    RawWat("br 1"),
                                ),
                            ),
                        )
                    ),
                    //destroyThreadPool.functionData.call(),
                ),
            )

            return SupportLibrary(
                parallel.functionData
            )
        }
    }
}