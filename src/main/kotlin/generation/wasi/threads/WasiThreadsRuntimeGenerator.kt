package generation.wasi.threads

import generation.Generator
import ir.expression.Value
import ir.statement.Function
import ir.statement.Program
import wasm.*

class WasiThreadsRuntimeGenerator : Generator {
    override fun apply(program: Program) {
        val module = program.module

        // types
        val kernelType = module.addType(params = listOf(WasmValueType.i32), result = listOf())
        val threadSpawnType =
            module.addType(params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32))
        val threadStartType =
            module.addType(params = listOf(WasmValueType.i32, WasmValueType.i32), result = listOf())

        // imports
        val wasiThreadSpawnImport = WasmFunction(Index.next(module.functions), type = threadSpawnType, import = WasmImport("wasi", "thread-spawn"))
        module.functions.add(wasiThreadSpawnImport)

        // functions


        // globals
        val numThreads = WasmGlobal(
            Index.next(module.globals),
            WasmGlobalType(WasmValueType.i32, true),
            mutableListOf(
                Value(WasmValueType.i32, "8")
            )
        )
        module.globals.add(numThreads)

        // exports


        // function table

    }


}