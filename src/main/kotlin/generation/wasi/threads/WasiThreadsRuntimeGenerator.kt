package generation.wasi.threads

import generation.Generator
import ir.expression.*
import ir.statement.*
import ir.statement.Function
import wasm.*
import java.awt.image.Kernel

/*
class WasiThreadsRuntimeGenerator : Generator {
    override fun apply(program: Program) {
        val module = program.module

        // kernels
        val kernels =
            program.statements.filterIsInstance<Function>().filter { it.annotations.any { ann -> ann is Kernel } }

        // types
        val kernelType = module.addType(params = listOf(WasmValueType.i32), result = listOf())
        val threadSpawnType = module.addType(params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32))
        val threadStartType = module.addType(params = listOf(WasmValueType.i32, WasmValueType.i32), result = listOf())


        // table
        val kernelTable = WasmTable(
            Index.next(module.tables),
            kernels.size,
            kernels.size,
            WasmRefType.funcref,
        )
        module.tables.add(kernelTable)

        // elements
        val kernelsElementSegment = WasmElementSegment(kernelTable.index, 0, kernels.map { it.functionData.index })
        module.elementSegments.add(kernelsElementSegment)


        // imports
        val wasiThreadSpawnImport = WasmFunction(
            Index.next(module.functions), type = threadSpawnType, import = WasmImport("wasi", "thread-spawn")
        )
        module.functions.add(wasiThreadSpawnImport)

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
            val arg = Symbol(WasmScope.local, WasmValueType.i32, Index(1))
            val threadId = Symbol(WasmScope.local, WasmValueType.i32, Index(2))
            val functionIndex = Symbol(WasmScope.local, WasmValueType.i32, Index(3))

            // unsigned int thread_id = args & 0x0000FFFF;
            it.instructions.add(
                Assignment(
                    threadId, BinaryOP(
                        WasmValueType.i32, Operator.and, arg, Value(WasmValueType.i32, "0x0000FFFF")
                    )
                )
            )

            // unsigned int kernel_id = (args & 0xFFFF0000) >> 16;
            it.instructions.add(
                Assignment(
                    functionIndex,
                    BinaryOP(
                        WasmValueType.i32, Operator.shr.copy(signed = BitSign.u), BinaryOP(
                            WasmValueType.i32, Operator.and, arg, Value(WasmValueType.i32, "0xFFFF0000")
                        ), Value(WasmValueType.i32, "16")
                    ),
                )
            )

            // table[kernel_id](thread_id)
            it.instructions.add(
                IndirectFunctionCall(
                    kernelTable.index,
                    kernelType.index,
                    functionIndex,
                    listOf(threadId),
                    false,
                )
            )

            // unlock mutex
            it.instructions.add(
                FunctionCall(
                    unlockMutex.functionData.index, listOf(threadId), false
                )
            )

        }
        program.statements.add(wasiThreadStart)


        // globals
        val numThreads = WasmGlobal(
            Index.next(module.globals), WasmGlobalType(WasmValueType.i32, true), mutableListOf(
                Value(WasmValueType.i32, "8")
            )
        )
        module.globals.add(numThreads)

        // exports
        val wasiThreadStartExport = WasmExport(
            "wasi_thread_start",
            WasmExportKind.func,
            wasiThreadStart.functionData.index,
        )
        module.exports.add(wasiThreadStartExport)

    }

}*/
