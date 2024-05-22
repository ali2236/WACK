package generation.wasi.threads

import ir.statement.Program
import wasm.Index
import wasm.WasmFunction
import wasm.WasmImport
import wasm.WasmValueType

object WasiThreadSpawnGenerator {
    fun generate(program: Program) : WasmFunction {
        val module = program.module

        // function type
        val threadSpawnType = module.findOraddType(params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32))

        // imports
        val wasiThreadSpawnImport = WasmFunction(
            Index.next(module.functions), type = threadSpawnType, import = WasmImport("wasi", "thread-spawn")
        )
        module.functions.add(wasiThreadSpawnImport)

        return wasiThreadSpawnImport

    }
}