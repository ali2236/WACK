package generation.wack

import generation.wasm.threads.MutexLibrary
import ir.expression.BinaryOP
import ir.expression.Symbol
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
            meta: MetaLibrary
        ): WasiThreadStart {
            val kernelType = program.module.findOrAddType(params = listOf(WasmValueType.i32)).index
            val tid = Symbol.localI32(Index.number(1))
            val mutex = Symbol.localI32(Index.number(2))
            val state = Symbol.localI32(Index.number(3))
            val wasiThreadStart = program.addFunction(
                name = "wasi_thread_start",
                params = listOf(WasmValueType.i32, WasmValueType.i32),
                locals = listOf(WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    wackThread.setState(tid, WackThread.State.Started),
                    Assignment(mutex, wackThread.getMutex(tid)),
                    tid,
                    meta.kernelId.get.call().result,
                    RawWat("call_indirect ${kernelTable.index} (type $kernelType)"),
                    mutexLib.unlock.call(mutex),
                    /*Block(
                        mutableListOf(
                            Loop(
                                mutableListOf(
                                    Assignment(state, wackThread.getState(tid)),
                                    // mutexLib.lock.call(mutex),
                                    If(
                                        condition = BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.eq,
                                            state,
                                            WackThread.State.SignalExit.value(),
                                        ),
                                        trueBody = mutableListOf(RawWat("br 1")),
                                    ),
                                    If(
                                        condition = BinaryOP(
                                            WasmValueType.i32,
                                            BinaryOP.Operator.eq,
                                            state,
                                            WackThread.State.HasTask.value(),
                                        ),
                                        trueBody = mutableListOf(
                                            wackThread.setState(tid, WackThread.State.RunningTask),
                                            tid,
                                            meta.kernelId.get.call().result,
                                            RawWat("call_indirect ${kernelTable.index} (type $kernelType)"),
                                            wackThread.setState(tid, WackThread.State.TaskDone),
                                        ),
                                    ),
                                    mutexLib.unlock.call(mutex),
                                    RawWat("br 1"),
                                ),
                            ),
                            wackThread.setState(tid, WackThread.State.Stopped),
                        ),
                    ),*/
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