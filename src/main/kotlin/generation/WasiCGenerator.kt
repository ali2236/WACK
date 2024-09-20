package generation

import external.Wasm2Wat
import external.WasmMerge
import generation.runtime2.CallKernelGenerator
import generation.runtime2.ImportRuntime2
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.KernelTableGenerator
import ir.Mode
import ir.annotations.CallKernel
import ir.annotations.Parallel
import ir.expression.SingleResultFunction
import ir.expression.Value
import ir.statement.Program
import ir.wasm.WasmValueType
import java.io.File

/*
class WasiCGenerator(val outputName: String) : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        val runtime = ImportRuntime2.into(program)
        val threadCountCall = SingleResultFunction(runtime.threadCount.call(), WasmValueType.i32)
        ThreadKernelGenerator.generate(program, threadCountCall){ _, block ->
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            val isParallel = block.hasAnnotation(Parallel::class.java)
            if(!isParallel) return@generate
            block.annotations.removeIf { it is Parallel }
            block.instructions.add(
                runtime.parallel.call(Value(WasmValueType.i32, "$kernelId"))
            )
        }
        val kernelTabel = KernelTableGenerator.generate(program)
        CallKernelGenerator.generate(program, kernelTabel)

        // TODO: make effected program memories shared
        program.module.memories.forEach {
            it.shared = true
            if(it.max == null){
                it.max = it.min
            }
        }

        val wackRuntime = File("./runtime/runtime3.wasm")

        // export program
        val wack = program.exportAsWasm(File("./out/intermediate/wack_program.wat"))

        // link with [wasm-merge]
        val output = WasmMerge.merge(listOf(
            Pair("wack", wack),
            Pair("wack_runtime", wackRuntime),
        ),outputName)

        Wasm2Wat.process(output)
    }
}*/
