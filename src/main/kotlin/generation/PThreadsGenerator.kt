package generation

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

class PThreadsGenerator(val outputName: String) : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        Mode.insure(Mode::callByIndex, true)
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

        // compile runtime2 with wack
        val runtime2 = Program.from(File("./runtime/runtime2.wasm"))
        val wackRuntime = runtime2.exportAsWasm(File("./out/intermediate/wack_runtime.wat"))

        // export program
        //val wack = program.exportAsWasm(File("./out/intermediate/wack_program.wat"))

        // link with [wasm-merge]
       /* WasmMerge.merge(listOf(
            Pair("wack", wack),
            Pair("wack_runtime", wackRuntime),
        ),outputName)*/
    }
}