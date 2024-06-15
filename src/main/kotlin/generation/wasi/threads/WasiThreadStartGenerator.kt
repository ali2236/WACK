package generation.wasi.threads

import generation.wack.ThreadArg
import generation.wasm.threads.MutexLibrary
import ir.annotations.Kernel
import ir.expression.*
import ir.expression.Assignment
import ir.statement.Function
import ir.statement.Program
import ir.statement.RawWat
import ir.wasm.*

object WasiThreadStartGenerator {
    fun generate(program: Program, arg: ThreadArg, mutex: MutexLibrary) {
        val module = program.module

        // kernels
        val kernels =
            program.statements
                .filterIsInstance<Function>()
                .filter { it.annotations.any { ann -> ann is Kernel } }
                .sortedBy { it.annotations.filterIsInstance<Kernel>().first().kernelId }

        // types
        val kernelType = module.findOraddType(params = listOf(WasmValueType.i32), result = listOf())
        val threadStartType =
            module.findOraddType(params = listOf(WasmValueType.i32, WasmValueType.i32), result = listOf())

        // table
        val kernelTable = WasmTable(
            Index.next(module.tables),
            kernels.size,
            kernels.size,
            WasmRefType.funcref,
        )
        module.tables.add(kernelTable)

        // elements
        val kernelsElementSegment = WasmElementSegment(
            Index.next(module.elementSegments),
            kernelTable.index,
            listOf(Value.zero),
            kernels.map { it.functionData.index }
        )
        module.elementSegments.add(kernelsElementSegment)

        // function headers
        val wasmWasiThreadStart = WasmFunction(
            Index.next(module.functions),
            "wasi_thread_start",
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
            it.instructions.add(
                Assignment(kernelIndex, arg.decode.call(args))
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