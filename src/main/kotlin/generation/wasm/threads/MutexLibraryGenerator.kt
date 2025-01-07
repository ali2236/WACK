package generation.wasm.threads

import compiler.WAPC
import generation.debug.PrintLibrary
import ir.expression.*
import ir.statement.*
import ir.statement.Function
import ir.wasm.*

class MutexLibrary(
    val lock: WasmFunction,
    val unlock: WasmFunction,
    val join: WasmFunction,
) {

    fun criticalSection(mutex: Expression = Value.i32(16384), task: () -> Statement) : Array<Statement>{
        val t = task()
        if (t is Empty){
            return arrayOf()
        }
        return arrayOf(
            lock.call(mutex),
            t,
            unlock.call(mutex),
        )
    }

    companion object {
        fun generate(program: Program, memory: WasmMemory, print: PrintLibrary): MutexLibrary {
            val m = memory.index
            val i = if (WAPC.params.multipleMemories) "$m" else ""

            // functions
            val tryLockMutex = program.addFunction(
                name = "wack__try_lock_mutex",
                params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32), instructions = mutableListOf(
                    RawWat("local.get 0"),
                    RawWat("i32.const 0"),
                    RawWat("i32.const 1"),
                    RawWat("i32.atomic.rmw.cmpxchg $i"),
                    RawWat("i32.eqz"),
                )
            )

            val lockMutex = program.addFunction(
                name = "wack__lock_mutex",
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
                                    RawWat("memory.atomic.wait32 $i"),
                                    RawWat("drop"),
                                    RawWat("br 0"),
                                )
                            ),
                        ),
                    ),
                    //print.print(Symbol.localI32(Index.number(0)), Value.one),
                ),
            )

            val unlockMutex = program.addFunction(
                name = "wack__unlock_mutex",
                params = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    RawWat("local.get 0"),
                    RawWat("i32.const 0"),
                    RawWat("i32.atomic.store $i"),
                    RawWat("local.get 0"),
                    RawWat("i32.const 1"),
                    RawWat("memory.atomic.notify $i"),
                    RawWat("drop"),
                    //print.print(Symbol.localI32(Index.number(0)), Value.zero),
                ),
            )

            val waitMutexLock = program.addFunction(
                name = "wack__wait_mutex_lock",
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
            )
        }
    }
}