package generation

import external.Wasm2Wat
import external.WasmMerge
import generation.runtime2.CallKernelGenerator
import generation.runtime2.ImportRuntime2
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.KernelTableGenerator
import generation.wasi.threads.WasiThreadsMemory
import ir.Mode
import ir.annotations.CallKernel
import ir.expression.Value
import ir.statement.Program
import java.io.File

class PThreadsGenerator(val outputName: String) : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        val runtime = ImportRuntime2.into(program)
        val threadCountCall = runtime.threadCount.call().result
        ThreadKernelGenerator.generate(program, threadCountCall){ _, block ->
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            block.instructions.add(runtime.parallel.call(Value.i32(kernelId)))
        }
        val kernelTable = KernelTableGenerator.generate(program)
        CallKernelGenerator.generate(program, kernelTable)
        WasiThreadsMemory(false).apply(program)

        val wackRuntime = File("./runtime/runtime2.wasm")

        // export program
        val wack = program.exportAsWasm(File("./out/intermediate/wack_program.wat"))

        // link with [wasm-merge]
        val output = WasmMerge.merge(listOf(
            Pair("wack_runtime", wackRuntime),
            Pair("wack", wack),
        ),outputName)

        Wasm2Wat.process(output)
    }
}