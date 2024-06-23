package generation

import generation.runtime2.ImportRuntime2
import generation.wack.ThreadKernelGenerator
import generation.wasi.threads.KernelTableGenerator
import ir.Mode
import ir.annotations.CallKernel
import ir.annotations.Parallel
import ir.expression.Value
import ir.statement.Program
import ir.wasm.WasmValueType

class PThreadsGenerator : Generator {
    override fun apply(program: Program) {
        Mode.insure(Mode::multipleMemories, true)
        Mode.insure(Mode::callByIndex, true)
        val runtime = ImportRuntime2.into(program)
        ThreadKernelGenerator.generate(program, runtime.threadCount){ _, block ->
            val kernelId = block.annotations.filterIsInstance<CallKernel>().first().kernelIndex
            val isParallel = block.hasAnnotation(Parallel::class.java)
            if(!isParallel) return@generate
            block.annotations.removeIf { it is Parallel }
            block.instructions.add(
                runtime.parallel.call(Value(WasmValueType.i32, "$kernelId"))
            )
        }
        KernelTableGenerator.generate(program)
    }
}