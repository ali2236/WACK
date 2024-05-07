package wasm

import org.antlr.v4.runtime.tree.ParseTree

data class WasmFunction(
    val index: Index,
    val name: String? = null,
    val type : WasmFunctionType,
    val locals : MutableList<WasmValueType> = mutableListOf(),
    val import: WasmImport? = null,
    val exportName: String? = null,
    val code: ParseTree? = null,
)