package generation

import compiler.WAPC
import generation.debug.PrintLibrary
import generation.wack.*
import generation.wasi.threads.KernelTableGenerator
import ir.Mode
import generation.wasi.threads.WasiThreadsLibrary
import generation.wasi.threads.WasiThreadsMemory
import generation.wasm.threads.MutexLibrary
import ir.annotations.CallKernel
import ir.annotations.TransferIn
import ir.annotations.TransferOut
import ir.expression.Value
import ir.statement.Assignment
import ir.statement.Program

class WasiThreadsGenerator : Generator {
    override fun apply(program: Program) {
        if (!WAPC.params.parallelize) return
        //Mode.insure("multipleMemories", WAPC.params.multipleMemories, true)
        val print = PrintLibrary.load(program)
        val metaLib = MetaLibrary.generate(program)
        val wackThread = WackThread.generate(program)
        val mutex = MutexLibrary.generate(program, wackThread.threadsMemory, print.disable)
        val wasiThreads = WasiThreadsLibrary.generate(program)
        val supportLibrary = SupportLibrary.generate(program, mutex, metaLib, wackThread, wasiThreads, print)
        ThreadKernelGenerator.generate(program, metaLib, mutex, print) { function, block ->
            WAPC.stats.loopsParallelized++
            block.instructions.clear()
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            block.annotations.filterIsInstance<TransferIn>().forEach { t ->
                block.instructions.add(metaLib.localTransfer(t.symbol.type).save.call(t.symbol, Value.i32(t.index)))
            }
            block.instructions.add(supportLibrary.parallel.call(Value.i32(kernelId)))
            block.annotations.filterIsInstance<TransferOut>().forEach { t ->
                block.instructions.add(
                    Assignment(
                        t.symbol,
                        metaLib.localTransfer(t.symbol.type).load.call(Value.i32(t.index)).result
                    )
                )
            }
        }

        // generate this table after generating kernels
        val kernelTable = KernelTableGenerator.generate(program)
        WasiThreadStart.generate(program, mutex, wackThread, kernelTable, metaLib, print)
        WasiThreadsMemory().apply(program)
    }
}