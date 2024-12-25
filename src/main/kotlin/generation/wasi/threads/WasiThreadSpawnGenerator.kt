package generation.wasi.threads

import compiler.WAPC
import ir.statement.Function
import ir.statement.Program
import ir.wasm.Index
import ir.wasm.WasmFunction
import ir.wasm.WasmImport
import ir.wasm.WasmValueType

@Deprecated("use WasiThreadsLibrary")
object WasiThreadSpawnGenerator {
    fun generate(program: Program) : WasmFunction {
        val module = program.module

        // function type
        val threadSpawnType = module.findOrAddType(params = listOf(WasmValueType.i32), result = listOf(WasmValueType.i32))

        // imports
        val wasiThreadSpawnImport = WasmFunction(
            Index("wasi_thread_spawn"),
            type = threadSpawnType, import = WasmImport("\"${WAPC.params.threadSpawnModule}\"", "\"thread-spawn\"")
        )
        module.functions.add(wasiThreadSpawnImport)

        // function
        val threadSpawn = Function(wasiThreadSpawnImport)
        program.statements.add(0, threadSpawn)

        return wasiThreadSpawnImport

    }
}