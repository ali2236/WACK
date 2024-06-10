package generation

interface WebAssemblyBlock : WebAssemblyInstruction {
    fun watHeader(wat: WatWriter)

    fun watEnd(wat: WatWriter)
}