package generation

interface WebAssemblyBlock : WebAssemblyInstruction {
    fun watHeader(wat: WatWriter)
}