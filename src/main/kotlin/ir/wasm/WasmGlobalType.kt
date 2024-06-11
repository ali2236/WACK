package ir.wasm

data class WasmGlobalType(val type: WasmValueType, val mutable: Boolean){
    override fun toString(): String {
        val mut = if(mutable) "mut " else ""
        return "$mut$type"
    }
}