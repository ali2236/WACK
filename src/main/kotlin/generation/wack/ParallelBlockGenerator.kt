package generation.wack

import generation.wasm.threads.MutexLibrary
import ir.annotations.Parallel
import ir.expression.*
import ir.statement.*
import ir.wasm.Index
import ir.wasm.WasmBitSign
import ir.wasm.WasmFunction
import ir.wasm.WasmGlobal
import ir.wasm.WasmScope
import ir.wasm.WasmValueType

// for thread_id from 0 to num_threads:
//    lockMutex(thread_id)
//    @{oldBody}
//
// for thread_id from 0 to num_threads:
//    join(thread_id)
//
object ParallelBlockGenerator {
    fun generate(
        program: Program, threadCount: Expression, mutex: MutexLibrary, arg: ThreadArg, threadSpawn: WasmFunction
    ): WasmFunction {
        val kernelId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(0))
        val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(1))
        val mutexAddress = BinaryOP(
            WasmValueType.i32,
            BinaryOP.Operator.mul,
            threadId,
            Value.i32(4),
        )
        val parallel = program.addFunction(
            name = "wack__parallel",
            params = listOf(WasmValueType.i32),
            locals = listOf(WasmValueType.i32),
            instructions = mutableListOf(
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
                                mutex.lock.call(mutexAddress),
                                If(
                                    condition = BinaryOP(
                                        WasmValueType.i32,
                                        BinaryOP.Operator.lt.copy(signed = WasmBitSign.u),
                                        threadSpawn.call(arg.encode.call(threadId, kernelId).result).result,
                                        Value.zero,
                                    ),
                                    trueBody = mutableListOf(Unreachable()),
                                ),
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
                                mutex.join.call(mutexAddress),
                                Assignment(threadId, BinaryOP.increment(threadId)),
                                RawWat("br 1"),
                            ),
                        ),
                    )
                ),
            ),
        )

        return parallel.functionData
    }
}