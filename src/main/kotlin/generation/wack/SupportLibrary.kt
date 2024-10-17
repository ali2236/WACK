package generation.wack

import WAPC
import generation.debug.PrintLibrary
import generation.debug.WasmAssert
import generation.wasi.threads.WasiThreadsLibrary
import generation.wasm.threads.MutexLibrary
import ir.expression.BinaryOP
import ir.expression.Load
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
                    If(
                        condition = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.eq,
                            meta.threadPoolState.get.call().result,
                            Value.one,
                        ),
                        trueBody = mutableListOf(Return())
                    ),
                    print.print(Value.i32(500)),
                    // set_max_threads(0);
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
                                    //wackThread.setMutex1(makeThreadPoolTid, Value.zero),
                                    //wackThread.setMutex2(makeThreadPoolTid, Value.one),
                                    // if(thread_spawn(i) < 0) exit(1)
                                    If(
                                        condition = BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.lt.copy(signed = WasmBitSign.u),
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
                    // set_max_threads(max_threads);
                    meta.maxThreads.set.call(makeThreadPoolMaxThreads),
                ),
            )

            val destroyThreadPool = program.addFunction(
                name = "destroy_thread_pool",
                locals = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    print.print(Value.i32(502)),
                    If(
                        condition = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.eq,
                            meta.threadPoolState.get.call().result,
                            Value.zero,
                        ),
                        trueBody = mutableListOf(Return())
                    ),
                    // set_thread_pool_state(0);
                    meta.threadPoolState.set.call(Value.zero),
                ),
            )


            val kernelId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(0))
            val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(1))
            val threadCount = Symbol(WasmScope.local, WasmValueType.i32, Index.number(2))
            val parallel = program.addFunction(
                name = "wack__parallel",
                params = listOf(WasmValueType.i32),
                locals = listOf(WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    print.print(Value.i32(300), kernelId),
                    meta.maxThreads.set.call(Value.i32(WAPC.params!!.threads)),
                    //makeThreadPool.functionData.call(Value.i32(WAPC.params!!.threads)), // TODO: move to somewhere else
                    meta.kernelId.set.call(kernelId),
                    Assignment(threadCount, meta.getMaxThreads),
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
                                    mutex.lock.call(wackThread.getMutex1(threadId)),
                                    print.print(kernelId, threadId),
                                    //mutex.unlock.call( wackThread.getMutex2(threadId)),
                                    If(
                                        condition = BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.lt.copy(signed = WasmBitSign.u),
                                            wasiThreads.spawnThread.call(threadId).result,
                                            Value.zero,
                                        ),
                                        trueBody = mutableListOf(Unreachable()),
                                    ),
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