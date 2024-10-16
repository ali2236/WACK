package generation.wack

import generation.debug.PrintLibrary
import generation.debug.WasmAssert
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
            val mutex2Load = wackThread.readMutex2(tid)
            val wasiThreadStart = program.addFunction(
                name = "wasi_thread_start",
                params = listOf(WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    WasmAssert.equal(wackThread.readMutex1(tid), Value.zero),
                    WasmAssert.equal(wackThread.readMutex2(tid), Value.one),
                    Loop(
                        instructions = mutableListOf(
                            WasmAssert.equal(wackThread.readMutex1(tid), Value.zero),
                            WasmAssert.equal(wackThread.readMutex2(tid), Value.one),
                            mutexLib.lock.call(wackThread.getMutex2(tid)),
                            mutexLib.unlock.call(wackThread.getMutex2(tid)),
                            IndirectFunctionCall(
                                kernelTable.index,
                                kernelType,
                                functionIndex = meta.kernelId.get.call().result,
                                params = mutableListOf(tid),
                                returnType = listOf()
                            ),
                            /*If(
                                condition = BinaryOP(
                                    WasmValueType.i32,
                                    BinaryOP.Operator.eq,
                                    mutex2Load,
                                    Value.zero,
                                ),
                                trueBody = mutableListOf(mutexLib.lock.call(mutex2)),
                            ),*/
                            mutexLib.lock.call(wackThread.getMutex2(tid)),
                            mutexLib.unlock.call(wackThread.getMutex1(tid)),
                            WasmAssert.equal(wackThread.readMutex1(tid), Value.zero),
                            WasmAssert.equal(wackThread.readMutex2(tid), Value.one),
                            //Return(),
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