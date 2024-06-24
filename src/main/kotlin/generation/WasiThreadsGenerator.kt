package generation

import ir.Mode
import generation.wack.ParallelBlockGenerator
import generation.wack.ThreadArgEncoderGenerator
import generation.wack.ThreadCountGenerator
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.WasiThreadSpawnGenerator
import generation.wasi.threads.WasiThreadStartGenerator
import generation.wasm.threads.MutexLibraryGenerator
import ir.annotations.CallKernel
import ir.annotations.ThreadId
import ir.expression.BinaryOP
import ir.expression.FunctionResult
import ir.expression.Value
import ir.statement.If
import ir.statement.Program
import ir.statement.Unreachable
import ir.wasm.WasmBitSign
import ir.wasm.WasmValueType

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        Mode.insure(Mode::callByIndex, true)
        val mutex = MutexLibraryGenerator.generate(program)
        val threadArg = ThreadArgEncoderGenerator.generate(program)
        val threadCount = ThreadCountGenerator.generate(program)
        val threadSpawn = WasiThreadSpawnGenerator.generate(program)
        val parallelBlocks = ThreadKernelGenerator.generate(program, threadCount.symbol){ function, block ->
            val threadId = function.annotations.filterIsInstance<ThreadId>().first().symbol
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            // lock mutex
            block.instructions.add(
                mutex.lock.call(threadId),
            )
            // spawn thread
            block.instructions.add(threadArg.encode.call(threadId, Value(WasmValueType.i32, "$kernelId")))
            block.instructions.add(threadSpawn.call(FunctionResult(WasmValueType.i32)))
            block.instructions.add(
                If(
                    BinaryOP(
                        WasmValueType.i32,
                        BinaryOP.Operator.lt.copy(signed = WasmBitSign.s),
                        FunctionResult(WasmValueType.i32),
                        Value.zero
                    ), mutableListOf(Unreachable())
                ),
            )
        }
        WasiThreadStartGenerator.generate(program, threadArg, mutex)
        ParallelBlockGenerator.generate(parallelBlocks, threadCount, mutex)
        program.module.memories.forEach {
            it.shared = true
            if(it.max == null){
                it.max = it.min
            }
        }
    }
}