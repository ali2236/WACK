package generation

import ir.Mode
import generation.wack.ParallelBlockGenerator
import generation.wack.ThreadArgEncoderGenerator
import generation.wack.ThreadCountGenerator
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.WasiThreadSpawnGenerator
import generation.wasi.threads.WasiThreadStartGenerator
import generation.wasm.threads.MutexLibraryGenerator
import ir.statement.Program

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        if(!Mode.multipleMemories){
            throw Exception("Mode.MultipleMemories must be enabled!")
        }
        if(Mode.callByIndex){
            throw Exception("Mode.callByIndex must be disabled!")
        }
        val mutex = MutexLibraryGenerator.generate(program)
        val threadArg = ThreadArgEncoderGenerator.generate(program)
        val threadCount = ThreadCountGenerator.generate(program)
        val threadSpawn = WasiThreadSpawnGenerator.generate(program)
        val parallelBlocks = ThreadKernelGenerator.generate(program, threadCount, threadSpawn, threadArg, mutex)
        WasiThreadStartGenerator.generate(program, threadArg, mutex)
        ParallelBlockGenerator.generate(parallelBlocks, threadCount, mutex)
    }
}