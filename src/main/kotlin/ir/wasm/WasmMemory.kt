package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.Mode
import ir.Names

data class WasmMemory(
    val index: Index,
    val min: Int,
    var max: Int?,
    var shared: Boolean?,
    var import: WasmImport? = null
) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        val sh = if (shared != null) if (shared as Boolean) " shared" else "" else ""
        val mx = if (max != null) " $max" else ""
        val mem = "(memory ${index.section(Names.memory)} $min${mx}$sh)"
        val all = if(import != null) "(import ${import!!.module} ${import!!.name} $mem)" else mem
        wat.writeLine(all)
    }

}
