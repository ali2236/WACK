package generation.wasi.threads

import generation.wack.ThreadArg
import generation.wasm.threads.MutexLibrary
import ir.expression.*
import ir.statement.*
import ir.wasm.*

/*
export function thread_start(int tid, int args){
    thread_id, kernel_index = decode_args(args)
    table[kernel_id](thread_id)
    unlockMutex(thread_id)
}
 */
object WasiThreadStartGenerator {
    fun generate(program: Program, arg: ThreadArg, mutex: MutexLibrary, threadCount: Expression) {
        val module = program.module

        // kernel table
        val kernelTable = KernelTableGenerator.generate(program)
        val kernelType = module.findOrAddType(params = listOf(WasmValueType.i32)).index

        val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(2))
        val kernelId = Symbol(WasmScope.local, WasmValueType.i32, Index.number(3))


        val wasiThreadStart = program.addFunction(
            name = "wack__wasi_thread_start",
            params = listOf(WasmValueType.i32, WasmValueType.i32),
            locals = listOf(WasmValueType.i32, WasmValueType.i32),
            instructions = mutableListOf(
                RawWat("local.get 1"),
                arg.decodeThreadId.call(), // thread_id = decode_tid(arg)
                RawWat("local.set 2"),
                RawWat("local.get 1"),
                arg.decodeKernelId.call(), // kernel_id = decode_kid(arg)
                RawWat("local.set 3"),
                RawWat("local.get 2"),
                RawWat("local.get 3"),
                RawWat("call_indirect ${kernelTable.index} (type $kernelType)"),
                RawWat("local.get 2"),
                RawWat("i32.const 4"),
                RawWat("i32.mul"),
                mutex.unlock.call(),
            ),
        )

        // exports
        val wasiThreadStartExport = WasmExport(
            "\"wasi_thread_start\"",
            WasmExportKind.func,
            wasiThreadStart.functionData.index,
        )
        module.exports.add(wasiThreadStartExport)
    }
}