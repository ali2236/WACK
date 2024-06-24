package generation.wasi.threads

import generation.wack.ThreadArg
import generation.wasm.threads.MutexLibrary
import ir.annotations.Kernel
import ir.expression.*
import ir.statement.*
import ir.statement.Function
import ir.wasm.*

/*
export function thread_start(int tid, int args){
    thread_id, kernel_index = decode_args(args)
    table[kernel_id](thread_id)
    unlockMutex(thread_id)
}
 */
object WasiThreadStartGenerator {
    fun generate(program: Program, arg: ThreadArg, mutex: MutexLibrary) {
        val module = program.module

        // types
        val kernelType = module.findOraddType(params = listOf(WasmValueType.i32), result = listOf())
        val threadStartType =
            module.findOraddType(params = listOf(WasmValueType.i32, WasmValueType.i32), result = listOf())

        // kernel table
        val kernelTable = KernelTableGenerator.generate(program)

        // function headers
        val wasmWasiThreadStart = WasmFunction(
            Index.next(module.functions),
            threadStartType,
            mutableListOf(WasmValueType.i32, WasmValueType.i32)
        )
        module.functions.add(wasmWasiThreadStart)

        // functions
        val wasiThreadStart = Function(wasmWasiThreadStart).also {
            val args = Symbol(WasmScope.local, WasmValueType.i32, Index(1))
            val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index(2))
            val kernelIndex = Symbol(WasmScope.local, WasmValueType.i32, Index(3))

            // thread_id, kernel_index = decode_arg(args)
            it.instructions.add(arg.decode.call(args))
            it.instructions.add(
                Assignment(kernelIndex, FunctionResult(WasmValueType.i32))
            )
            it.instructions.add(RawWat("local.set ${threadId.index}"))


            // table[kernel_id](thread_id)
            it.instructions.add(
                IndirectFunctionCall(
                    kernelTable.index,
                    kernelType.index,
                    kernelIndex,
                    listOf(threadId),
                    kernelType.result,
                )
            )

            // unlock mutex
            it.instructions.add(
                mutex.unlock.call(threadId),
            )

        }
        program.statements.add(wasiThreadStart)

        // exports
        val wasiThreadStartExport = WasmExport(
            "\"wasi_thread_start\"",
            WasmExportKind.func,
            wasiThreadStart.functionData.index,
        )
        module.exports.add(wasiThreadStartExport)
    }
}