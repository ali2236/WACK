package generation.runtime2

import ir.statement.Program
import java.io.File

object ImportRuntime2 {
    fun into(program: Program) : WackPthreads {
        val runtimeWasm = File("./runtime/runtime2.wasm")
        val module = Program.from(runtimeWasm)

        return TODO()
    }
}