package generation.debug

import ir.statement.Function
import ir.statement.Program
import ir.wasm.*

class PrintLibrary(
    val printI32: WasmFunction,
    val print2xI32: WasmFunction,
) {
    companion object {
        fun generate(program: Program) : PrintLibrary {
            val module = program.module
            val dlModule = "\"print\""

            // Imports
            val printI32 = WasmFunction(
                Index("print_i32"),
                module.findOrAddType(params = listOf(WasmValueType.i32)),
                import = WasmImport(dlModule, "\"print_i32\""),
            )

            val print2xI32 = WasmFunction(
                Index("print_2_i32"),
                module.findOrAddType(params = listOf(WasmValueType.i32, WasmValueType.i32)),
                import = WasmImport(dlModule, "\"print_2_i32\""),
            )

            module.functions.addAll(listOf(printI32, print2xI32))

            // Functions
            program.statements.addAll(0, listOf(
                Function(printI32),
                Function(print2xI32),
            ))

            return PrintLibrary(
                printI32,
                print2xI32,
            )
        }
    }
}