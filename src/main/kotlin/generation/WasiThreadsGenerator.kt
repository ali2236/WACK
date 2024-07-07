package generation

import ir.Mode
import generation.wack.ParallelBlockGenerator
import generation.wack.ThreadArgEncoderGenerator
import generation.wack.ThreadCountGenerator
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.WasiThreadSpawnGenerator
import generation.wasi.threads.WasiThreadStartGenerator
import generation.wasi.threads.WasiThreadsMemory
import generation.wasm.threads.MutexLibraryGenerator
import ir.annotations.CallKernel
import ir.annotations.ThreadId
import ir.expression.BinaryOP
import ir.expression.FunctionResult
import ir.expression.SingleResultFunction
import ir.expression.Value
import ir.statement.If
import ir.statement.Program
import ir.statement.Unreachable
import ir.wasm.*

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        val mutex = MutexLibraryGenerator.generate(program)
        val threadArg = ThreadArgEncoderGenerator.generate(program)
        val threadCount = ThreadCountGenerator(8).generate(program)
        val threadSpawn = WasiThreadSpawnGenerator.generate(program)
        val parallel = ParallelBlockGenerator.generate(program, threadCount.symbol, mutex, threadArg, threadSpawn)
        ThreadKernelGenerator.generate(program, threadCount.symbol) { function, block ->
            block.instructions.clear()
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            block.instructions.add(parallel.call(Value.i32(kernelId)))
        }
        WasiThreadStartGenerator.generate(program, threadArg, mutex, threadCount.symbol)
        WasiThreadsMemory().apply(program)
    }
}