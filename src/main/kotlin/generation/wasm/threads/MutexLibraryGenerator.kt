package generation.wasm.threads

import ir.expression.*
import ir.statement.*
import ir.statement.Function
import wasm.*

object MutexLibraryGenerator {
    fun generate(program: Program) : MutexLibrary {
        val module = program.module

        // memory
        val mutexMemory = WasmMemory(Index.next(module.memories), 4, 4, true)
        module.memories.add(mutexMemory)

        // types
        val kernelType = module.findOraddType(params = listOf(WasmValueType.i32), result = listOf())
        val threadSpawnType = module.findOraddType(params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32))
        val argEncodeType =
            module.findOraddType(params = listOf(WasmValueType.i32, WasmValueType.i32), result = listOf(WasmValueType.i32))


        // function headers
        val wasmTryLockMutex = WasmFunction(Index.next(module.functions), "try_lock_mutex", threadSpawnType)
        module.functions.add(wasmTryLockMutex)
        val wasmLockMutex = WasmFunction(Index.next(module.functions), "lock_mutex", kernelType)
        module.functions.add(wasmLockMutex)
        val wasmUnlockMutex = WasmFunction(Index.next(module.functions), "unlock_mutex", kernelType)
        module.functions.add(wasmUnlockMutex)
        val wasmWaitMutexLock = WasmFunction(Index.next(module.functions), "wait_mutex_lock", kernelType)
        module.functions.add(wasmWaitMutexLock)
        val wasmArgEncode = WasmFunction(Index.next(module.functions), "arg_encode", argEncodeType)
        module.functions.add(wasmArgEncode)

        // functions
        val tryLockMutex = Function(
            wasmTryLockMutex, mutableListOf(
                Symbol(WasmScope.local, WasmValueType.i32, Index(0)),
                Value(WasmValueType.i32, "0"),
                Value(WasmValueType.i32, "1"),
                RawWat("i32.atomic.rmw.cmpxchg ${mutexMemory.index}"),
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
                                listOf(Symbol(WasmScope.local, WasmValueType.i32, Index(0))),
                                true,
                            ), block, 1
                        ),
                        Symbol(WasmScope.local, WasmValueType.i32, Index(0)),
                        Value(WasmValueType.i32, "1"),
                        Value(WasmValueType.i32, "-1"),
                        RawWat("memory.atomic.wait32 ${mutexMemory.index}"),
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
                RawWat("memory.atomic.notify ${mutexMemory.index}"),
                RawWat("drop"),
            )
        )
        program.statements.add(unlockMutex)
        val waitMutexLock = Function(
            wasmWaitMutexLock, mutableListOf(
                FunctionCall(
                    wasmLockMutex.index, listOf(Symbol(WasmScope.local, WasmValueType.i32, Index(0))), false
                ),
                FunctionCall(
                    wasmUnlockMutex.index, listOf(Symbol(WasmScope.local, WasmValueType.i32, Index(0))), false
                ),
            )
        )
        program.statements.add(waitMutexLock)

        return MutexLibrary(
            wasmLockMutex,
            wasmUnlockMutex,
            wasmWaitMutexLock,
            mutexMemory,
        )
    }
}