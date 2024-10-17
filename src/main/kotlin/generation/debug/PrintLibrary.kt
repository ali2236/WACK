package generation.debug

import ir.expression.Expression
import ir.statement.Empty
import ir.statement.Function
import ir.statement.Program
import ir.statement.Statement
import ir.wasm.*

abstract class PrintLibrary {
    abstract fun print(first: Expression, second: Expression? = null): Statement

    val disable : PrintLibrary
        get() = DisabledPrintLib()

    companion object {
        // Needs to be used with wasm-merge
        fun generate(program: Program): PrintLibrary {
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
            program.statements.addAll(
                0, listOf(
                    Function(printI32),
                    Function(print2xI32),
                )
            )

            return PrintLibProxy(
                printI32,
                print2xI32,
            )
        }

        fun load(program: Program): PrintLibrary {
            val module = program.module

            // find exports
            val printI32e = module.exports.firstOrNull { it.name == "\"print_i32\"" }
            val print2xI32e = module.exports.firstOrNull { it.name == "\"print_2_i32\"" }

            if (printI32e != null && print2xI32e != null) {
                // find functions
                val printI32 = module.functions.first { it.index == printI32e.index }
                val print2xI32 = module.functions.first { it.index == print2xI32e.index }

                return PrintLibProxy(printI32, print2xI32)
            } else {
                return DisabledPrintLib()
            }
        }
    }

    class PrintLibProxy(private val printI32: WasmFunction, private val print2I32: WasmFunction) : PrintLibrary() {
        override fun print(first: Expression, second: Expression?): Statement {
            return if (second != null){
                print2I32.call(second, first)
            } else {
                printI32.call(first)
            }
        }
    }

    class DisabledPrintLib : PrintLibrary() {
        override fun print(first: Expression, second: Expression?): Statement {
            return Empty()
        }
    }
}