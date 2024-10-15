package generation.wack

import generation.debug.PrintLibrary
import generation.wasm.threads.MutexLibrary
import ir.expression.BinaryOP
import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.*
import ir.wasm.*

class WasiThreadStart(
    val wasiThreadStart: WasmFunction
) {
    companion object {
        fun generate(
            program: Program,
            mutexLib: MutexLibrary,
            wackThread: WackThread,
            kernelTable: WasmTable,
            meta: MetaLibrary,
        ): WasiThreadStart {
            val kernelType = program.module.findOrAddType(params = listOf(WasmValueType.i32)).index
            val tid = Symbol.localI32(Index.number(1))
            val mutex1 = Symbol.localI32(Index.number(2))
            val mutex2 = Symbol.localI32(Index.number(3))
            val mutex2Load = Load(WasmValueType.i32, mutex2, wackThread.threadsMemory.index)
            val wasiThreadStart = program.addFunction(
                name = "wasi_thread_start",
                params = listOf(WasmValueType.i32, WasmValueType.i32),
                locals = listOf(WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    Assignment(mutex1, wackThread.getMutex1(tid)),
                    Assignment(mutex2, wackThread.getMutex2(tid)),
                    Loop(
                        instructions = mutableListOf(
                            mutexLib.join.call(mutex2),
                            IndirectFunctionCall(
                                kernelTable.index,
                                kernelType,
                                functionIndex = meta.kernelId.get.call().result,
                                params = mutableListOf(tid),
                                returnType = listOf()
                            ),
                            mutexLib.lock.call(mutex2),
                            mutexLib.unlock.call(mutex1),
                            RawWat("br 0"),
                        )
                    ),
                ),
            )

            // exports
            val wasiThreadStartExport = WasmExport(
                "\"wasi_thread_start\"",
                WasmExportKind.func,
                wasiThreadStart.functionData.index,
            )
            program.module.exports.add(wasiThreadStartExport)

            return WasiThreadStart(wasiThreadStart.functionData)
        }
    }
}