package generation.wack

import ir.expression.Value
import ir.statement.Program
import ir.wasm.*

object StackBaseGenerator {
    fun generate(program: Program) : WasmGlobal {
        val module = program.module

        // globals
        val stackBase = WasmGlobal(
            Index.next(module.globals), WasmGlobalType(WasmValueType.i32, true), mutableListOf(
                Value.i32(0),
            )
        )
        module.globals.add(stackBase)

        // exports
        val numThreadsExport = WasmExport(
            "\"wack__serial_function_stack_base\"",
            WasmExportKind.global,
            stackBase.index,
        )
        module.exports.add(numThreadsExport)

        return stackBase
    }
}