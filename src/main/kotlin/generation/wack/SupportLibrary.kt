package generation.wack

import WAPC
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
        ): SupportLibrary {

            // make_thread_pool
            val maxThreads = Symbol.localI32(Index.number(0))
            val tid = Symbol.localI32(Index.number(1))
            val makeThreadPool = program.addFunction(
                name = "make_thread_pool",
                params = listOf(WasmValueType.i32),
                locals = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    If(
                        condition = BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.eq,
                            meta.maxThreads.get.call().result,
                            Value.zero,
                        ),
                        trueBody = mutableListOf(
                            // set_max_threads(max_threads);
                            meta.maxThreads.set.call(maxThreads),
                            // for(int i=0;i<max_threads;i++)
                            Loop(
                                mutableListOf(
                                    If(
                                        condition = BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.lt.copy(signed = WasmBitSign.s),
                                            tid,
                                            maxThreads,
                                        ),
                                        trueBody = mutableListOf(
                                            // set_thread_property(i, __wack_thread_id, i);
                                            wackThread.setTid(tid, tid),
                                            // lock_mutex(get_thread_property_address(__wack_thread_mutex));
                                            mutex.lock.call(wackThread.getMutex(tid)),
                                            // if(thread_spawn(i) < 0) exit(1)
                                            If(
                                                condition = BinaryOP(
                                                    WasmValueType.i32,
                                                    BinaryOP.Operator.lt.copy(signed = WasmBitSign.u),
                                                    wasiThreads.spawnThread.call(tid).result,
                                                    Value.zero,
                                                ),
                                                trueBody = mutableListOf(Unreachable()),
                                            ),
                                            // i++;
                                            Assignment(tid, BinaryOP.increment(tid)),
                                            // continue;
                                            RawWat("br 1"),
                                        ),
                                    )
                                ),
                            ),
                        ),
                    ),
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
                    makeThreadPool.functionData.call(Value.i32(WAPC.params!!.threads)), // TODO: move to somewhere else
                    meta.kernelId.set.call(kernelId),
                    Assignment(threadCount, meta.maxThreads.get.call().result),
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
                                    wackThread.setState(threadId, WackThread.State.HasTask),
                                    mutex.unlock.call(wackThread.getMutex(threadId)),
                                    Assignment(threadId, BinaryOP.increment(threadId)),
                                    RawWat("br 1"),
                                ),
                            ),
                        )
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
                                    Loop(
                                        instructions = mutableListOf(
                                            If(
                                                condition = BinaryOP(
                                                    WasmValueType.i32,
                                                    BinaryOP.Operator.neq,
                                                    wackThread.getState(threadId),
                                                    WackThread.State.TaskDone.value(),
                                                ),
                                                trueBody = mutableListOf(RawWat("br 1")),
                                            ),
                                        ),
                                    ),
                                    mutex.lock.call(wackThread.getMutex(threadId)),
                                    Assignment(threadId, BinaryOP.increment(threadId)),
                                    RawWat("br 1"),
                                ),
                            ),
                        )
                    ),
                ),
            )

            return SupportLibrary(
                parallel.functionData
            )
        }
    }
}