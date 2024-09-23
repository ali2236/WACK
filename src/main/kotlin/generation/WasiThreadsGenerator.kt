package generation

import generation.wack.*
import ir.Mode
import generation.wasi.threads.WasiThreadSpawnGenerator
import generation.wasi.threads.WasiThreadStartGenerator
import generation.wasi.threads.WasiThreadsMemory
import generation.wasm.threads.MutexLibraryGenerator
import ir.annotations.CallKernel
import ir.annotations.StackBase
import ir.expression.Value
import ir.statement.Assignment
import ir.statement.Program

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        val mutex = MutexLibraryGenerator.generate(program)
        val threadArg = ThreadArgEncoderGenerator.generate(program)
        val threadCount = ThreadCountGenerator(8).generate(program)
        val metaLib = MetaLibrary.generate(program)
        val threadSpawn = WasiThreadSpawnGenerator.generate(program)
        val runParallel =
            RunParallelFunctionGenerator.generate(program, threadCount.symbol, mutex, threadArg, threadSpawn)
        ThreadKernelGenerator.generate(program, threadCount.symbol, metaLib) { function, block ->
            block.instructions.clear()
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            block.annotations.filterIsInstance<StackBase>().firstOrNull()?.symbol?.let { localStackBase ->
                block.instructions.add(metaLib.setStackBase.call(localStackBase))
            }
            block.instructions.add(runParallel.call(Value.i32(kernelId)))
        }
        WasiThreadStartGenerator.generate(program, threadArg, mutex, threadCount.symbol)
        WasiThreadsMemory().apply(program)
    }
}