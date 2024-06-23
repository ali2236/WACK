package generation.wasi.threads

import ir.annotations.Kernel
import ir.expression.Value
import ir.statement.Function
import ir.statement.Program
import ir.wasm.Index
import ir.wasm.WasmElementSegment
import ir.wasm.WasmRefType
import ir.wasm.WasmTable

object KernelTableGenerator {
    fun generate(program: Program) : WasmTable{
        val module = program.module

        // kernels
        val kernels =
            program.statements
                .filterIsInstance<Function>()
                .filter { it.annotations.any { ann -> ann is Kernel } }
                .sortedBy { it.annotations.filterIsInstance<Kernel>().first().kernelId }

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

        return kernelTable
    }
}