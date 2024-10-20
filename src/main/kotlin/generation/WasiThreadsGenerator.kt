package generation

import WAPC
import generation.debug.PrintLibrary
import generation.wack.*
import generation.wasi.threads.KernelTableGenerator
import ir.Mode
import generation.wasi.threads.WasiThreadsLibrary
import generation.wasi.threads.WasiThreadsMemory
import generation.wasm.threads.MutexLibrary
import ir.annotations.CallKernel
import ir.annotations.StackBase
import ir.expression.Value
import ir.statement.Program

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        if(!WAPC.params!!.parallelize) return
        Mode.insure("multipleMemories", WAPC.params!!.multipleMemories, true)
        val print = PrintLibrary.load(program)
        val metaLib = MetaLibrary.generate(program)
        val wackThread = WackThread.generate(program)
        val mutex = MutexLibrary.generate(program, wackThread.threadsMemory, print.disable)
        val wasiThreads = WasiThreadsLibrary.generate(program)
        val supportLibrary = SupportLibrary.generate(program, mutex, metaLib, wackThread, wasiThreads, print.disable)
        ThreadKernelGenerator.generate(program, metaLib, mutex, print) { function, block ->
            block.instructions.clear()
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            block.annotations.filterIsInstance<StackBase>().firstOrNull()?.symbol?.let { localStackBase ->
                block.instructions.add(metaLib.stackBase.set.call(localStackBase))
                //block.instructions.addAll(mutex.criticalSection { print.print(localStackBase) })
            }
            block.instructions.add(supportLibrary.parallel.call(Value.i32(kernelId)))
        }
        // generate this table after generating kernels
        val kernelTable = KernelTableGenerator.generate(program)
        WasiThreadStart.generate(program, mutex, wackThread, kernelTable, metaLib, print.disable)
        WasiThreadsMemory().apply(program)
    }
}