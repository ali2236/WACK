package generation

import generation.wat.WatWriter

interface WebAssemblyBlock : WebAssemblyInstruction {
    fun watHeader(wat: WatWriter)

    fun watEnd(wat: WatWriter)
}