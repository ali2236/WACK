package generation.runtime2

import ir.statement.Function
import ir.statement.Program
import ir.wasm.Index
import ir.wasm.WasmFunction
import ir.wasm.WasmImport
import ir.wasm.WasmValueType

object ImportRuntime2 {

    fun into(program: Program) : WackPthreads{
        val module = program.module

        // wack_parallel
        val wackParallelType = module.findOraddType(listOf(WasmValueType.i32), listOf())

        val wackParallel = WasmFunction(
            Index.next(module.functions),
            wackParallelType,
            import = WasmImport("\"wack_runtime\"", "\"parallel\"")
        )

        module.functions.add(wackParallel)
        program.statements.add(0, Function(wackParallel))

        // get_num_threads
        val getterType = module.findOraddType(listOf(), listOf(WasmValueType.i32))
        val getNumThreads = WasmFunction(
            Index.next(module.functions),
            getterType,
            import = WasmImport("\"wack_runtime\"", "\"get_num_threads\"")
        )
        module.functions.add(getNumThreads)
        program.statements.add(0, Function(getNumThreads))

        return WackPthreads(
            threadCount = getNumThreads,
            parallel = wackParallel,
        )
    }

}