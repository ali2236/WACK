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
            print: PrintLibrary,
        ): WasiThreadStart {
            val kernelType = program.module.findOrAddType(params = listOf(WasmValueType.i32)).index
            val tid = Symbol.localI32(Index.number(1))
            val wasiThreadStart = program.addFunction(
                name = "wasi_thread_start",
                params = listOf(WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    //*mutexLib.criticalSection { print.print(tid) },
                    //WasmAssert.equal(wackThread.readMutex1(tid), Value.zero),
                    //WasmAssert.equal(wackThread.readMutex2(tid), Value.one),
                    Loop(
                        instructions = mutableListOf(
                            //mutexLib.lock.call(wackThread.getMutex2(tid)),
                            //mutexLib.unlock.call(wackThread.getMutex2(tid)),
                            *mutexLib.criticalSection { print.print(tid, meta.kernelId.get.call().result) },
                            IndirectFunctionCall(
                                kernelTable.index,
                                kernelType,
                                functionIndex = meta.kernelId.get.call().result,
                                params = mutableListOf(tid),
                            ),
                            //mutexLib.lock.call(wackThread.getMutex2(tid)),
                            mutexLib.unlock.call(wackThread.getMutex1(tid)),
                            Return(), //RawWat("br 0"),
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