package generation.wack

import ir.expression.Symbol
import ir.expression.Value
import ir.statement.Program
import ir.wasm.*
import wasm.*

object ThreadCountGenerator {
    fun generate(program: Program) : WasmGlobal {
        val module = program.module

        // globals
        val numThreads = WasmGlobal(
            Index.next(module.globals), WasmGlobalType(WasmValueType.i32, true), mutableListOf(
                Value(WasmValueType.i32, "8")
            )
        )
        module.globals.add(numThreads)

        // exports
        val numThreadsExport = WasmExport(
            "\"num_threads\"",
            WasmExportKind.global,
            numThreads.index,
        )
        module.exports.add(numThreadsExport)

        return numThreads
    }
}