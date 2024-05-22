package generation

import generation.Generator
import generation.wack.ThreadArgEncoderGenerator
import generation.wack.ThreadCountGenerator
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.WasiThreadSpawnGenerator
import generation.wasm.threads.MutexLibraryGenerator
import ir.statement.Program

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        val mutex = MutexLibraryGenerator.generate(program)
        val threadArg = ThreadArgEncoderGenerator.generate(program)
        val threadCount = ThreadCountGenerator.generate(program)
        val threadSpawn = WasiThreadSpawnGenerator.generate(program)
        val kernels = ThreadKernelGenerator.generate(program, threadCount)
    }
}