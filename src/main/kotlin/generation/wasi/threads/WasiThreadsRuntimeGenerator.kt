package generation.wasi.threads

import generation.Generator
import ir.expression.*
import ir.statement.*
import ir.statement.Function
import wasm.*
import java.awt.image.Kernel

class WasiThreadsRuntimeGenerator : Generator {
    override fun apply(program: Program) {
        val module = program.module

        // kernels
        val kernels = program.statements
            .filterIsInstance<Function>()
            .filter { it.annotations.any { ann -> ann is Kernel } }

        // memory
        val runtimeMemory = WasmMemory(Index.next(module.memories), 4, 4, true)
        module.memories.add(runtimeMemory)

        // types
        val kernelType = module.addType(params = listOf(WasmValueType.i32), result = listOf())
        val threadSpawnType = module.addType(params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32))
        val threadStartType = module.addType(params = listOf(WasmValueType.i32, WasmValueType.i32), result = listOf())

        // table
        val kernelTable = WasmTable(Index.next(module.tables), kernels.size, kernels.size)
        module.tables.add(kernelTable)

        // elements
        val kernelsElementSegment = WasmElementSegment(
            kernelTable.index,
            0,
            kernels.map { it.functionData.index }
        )
        module.elementSegments.add(kernelsElementSegment)


        // imports
        val wasiThreadSpawnImport = WasmFunction(
            Index.next(module.functions), type = threadSpawnType, import = WasmImport("wasi", "thread-spawn")
        )
        module.functions.add(wasiThreadSpawnImport)

        // function headers
        val wasmTryLockMutex = WasmFunction(Index.next(module.functions), "try_lock_mutex", threadSpawnType)
        module.functions.add(wasmTryLockMutex)
        val wasmLockMutex = WasmFunction(Index.next(module.functions), "lock_mutex", kernelType)
        module.functions.add(wasmLockMutex)
        val wasmUnlockMutex = WasmFunction(Index.next(module.functions), "unlock_mutex", kernelType)
        module.functions.add(wasmUnlockMutex)
        val wasmWaitMutexLock = WasmFunction(Index.next(module.functions), "wait_mutex_lock", kernelType)
        module.functions.add(wasmWaitMutexLock)
        val wasmWasiThreadStart = WasmFunction(
            Index.next(module.functions),
            "wasi_thread_start",
            threadStartType,
            mutableListOf(WasmValueType.i32, WasmValueType.i32)
        )
        module.functions.add(wasmWasiThreadStart)

        // functions
        val tryLockMutex = Function(
            wasmTryLockMutex, mutableListOf(
                Symbol(WasmScope.local, WasmValueType.i32, Index(0)),
                Value(WasmValueType.i32, "0"),
                Value(WasmValueType.i32, "1"),
                RawWat("i32.atomic.rmw.cmpxchg ${runtimeMemory.index}"),
                RawWat("i32.eqz"),
            )
        )
        program.statements.add(tryLockMutex)
        val lockMutex = Function(wasmLockMutex, mutableListOf(Block().also { block ->
            block.instructions.add(
                Loop(
                    mutableListOf(
                        BrIf(
                            FunctionCall(
                                tryLockMutex.functionData.index,
                                listOf(Symbol(WasmScope.local, WasmValueType.i32, 0)),
                                true,
                            ), block, 1
                        ),
                        Symbol(WasmScope.local, WasmValueType.i32, Index(0)),
                        Value(WasmValueType.i32, "1"),
                        Value(WasmValueType.i32, "-1"),
                        RawWat("memory.atomic.wait32 ${runtimeMemory.index}"),
                        RawWat("drop"),
                        RawWat("br 0"),
                    ),
                ),
            )
        }))
        program.statements.add(lockMutex)
        val unlockMutex = Function(
            wasmUnlockMutex, mutableListOf(
                Symbol(WasmScope.local, WasmValueType.i32, Index(0)),
                Value(WasmValueType.i32, "0"),
                RawWat("i32.atomic.store"),

                Symbol(WasmScope.local, WasmValueType.i32, Index(0)),
                Value(WasmValueType.i32, "1"),
                RawWat("memory.atomic.notify ${runtimeMemory.index}"),
                RawWat("drop"),
            )
        )
        program.statements.add(unlockMutex)
        val waitMutexLock = Function(
            wasmWaitMutexLock, mutableListOf(
                FunctionCall(
                    wasmLockMutex.index,
                    listOf(Symbol(WasmScope.local, WasmValueType.i32, Index(0))),
                    false
                ),
                FunctionCall(
                    wasmUnlockMutex.index,
                    listOf(Symbol(WasmScope.local, WasmValueType.i32, Index(0))),
                    false
                ),
            )
        )
        program.statements.add(waitMutexLock)

        val wasiThreadStart = Function(wasmWasiThreadStart).also {
            val arg = Symbol(WasmScope.local, WasmValueType.i32, Index(1))
            val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index(2))
            val functionIndex = Symbol(WasmScope.local, WasmValueType.i32, Index(3))

            // unsigned int thread_id = args & 0x0000FFFF;
            it.instructions.add(
                Assignment(
                    threadId,
                    BinaryOP(
                        WasmValueType.i32,
                        Operator.and,
                        arg,
                        Value(WasmValueType.i32, "0x0000FFFF")
                    )
                )
            )

            // unsigned int kernel_id = (args & 0xFFFF0000) >> 16;
            it.instructions.add(
                Assignment(
                    functionIndex,
                    BinaryOP(
                        WasmValueType.i32,
                        Operator.shr.copy(signed = BitSign.u),
                        BinaryOP(
                            WasmValueType.i32,
                            Operator.and,
                            arg,
                            Value(WasmValueType.i32, "0xFFFF0000")
                        ),
                        Value(WasmValueType.i32, "16")
                    ),
                )
            )

            // table[kernel_id](thread_id)
            it.instructions.add(
                IndirectFunctionCall(
                    kernelTable.index,
                    kernelType.index,
                    functionIndex,
                    listOf(threadId),
                    false,
                )
            )

            // unlock mutex
            it.instructions.add(
                FunctionCall(
                    unlockMutex.functionData.index,
                    listOf(threadId),
                    false
                )
            )

        }
        program.statements.add(wasiThreadStart)


        // globals
        val numThreads = WasmGlobal(
            Index.next(module.globals), WasmGlobalType(WasmValueType.i32, true), mutableListOf(
                Value(WasmValueType.i32, "8")
            )
        )
        module.globals.add(numThreads)

        // exports
        val wasiThreadStartExport = WasmExport(
            "wasi_thread_start",
            WasmExportKind.func,
            wasiThreadStart.functionData.index,
        )
        module.exports.add(wasiThreadStartExport)

    }


}