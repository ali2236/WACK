package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmMemory(val index: Index, val min: Int, var max: Int?, var shared: Boolean?) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        val sh = if(shared != null) if(shared as Boolean) " shared" else "" else ""
        val mx = if(max != null) " $max" else ""
        wat.writeLine("(memory (;$index;) $min${mx}$sh)")
    }

}
