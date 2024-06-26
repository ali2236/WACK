package generation.wasm.threads

import ir.expression.*
import ir.statement.*
import ir.statement.Function
import ir.wasm.*

object MutexLibraryGenerator {
    fun generate(program: Program): MutexLibrary {
        val module = program.module

        // memory
        val m = Index.next(module.memories)
        val mutexMemory = WasmMemory(m, 4, 4, true)
        module.memories.add(mutexMemory)

        // functions
        val tryLockMutex = program.addFunction(
            params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32), instructions = mutableListOf(
                RawWat("local.get 0"),
                RawWat("i32.const 0"),
                RawWat("i32.const 1"),
                RawWat("i32.atomic.rmw.cmpxchg $m"),
                RawWat("i32.eqz"),
            )
        )

        val lockMutex = program.addFunction(
            params = listOf(WasmValueType.i32),
            instructions = mutableListOf(
                Block(
                    instructions = mutableListOf(
                        Loop(
                            instructions = mutableListOf(
                                RawWat("local.get 0"),
                                tryLockMutex.functionData.call(),
                                RawWat("br_if 1"),
                                RawWat("local.get 0"),
                                RawWat("i32.const 1"),
                                RawWat("i64.const -1"),
                                RawWat("memory.atomic.wait32 $m"),
                                RawWat("drop"),
                                RawWat("br 0"),
                            )
                        ),
                    )
                ),
            ),
        )

        val unlockMutex = program.addFunction(
            params = listOf(WasmValueType.i32),
            instructions = mutableListOf(
                RawWat("local.get 0"),
                RawWat("i32.const 0"),
                RawWat("i32.atomic.store $m"),
                RawWat("local.get 0"),
                RawWat("i32.const 1"),
                RawWat("memory.atomic.notify $m"),
                RawWat("drop"),
            ),
        )

        val waitMutexLock = program.addFunction(
            params = listOf(WasmValueType.i32),
            instructions = mutableListOf(
                RawWat("local.get 0"),
                lockMutex.functionData.call(),
                RawWat("local.get 0"),
                unlockMutex.functionData.call(),
            ),
        )

        return MutexLibrary(
            lock = lockMutex.functionData,
            unlock = unlockMutex.functionData,
            join = waitMutexLock.functionData,
            memory = mutexMemory,
        )
    }
}