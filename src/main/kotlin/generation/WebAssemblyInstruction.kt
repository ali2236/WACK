package generation

import generation.c.CWriter
import generation.wat.WatWriter

interface WebAssemblyInstruction {

    fun wat(wat : WatWriter)

    fun c(writer: CWriter) {}

}