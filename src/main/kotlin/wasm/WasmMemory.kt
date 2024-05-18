package wasm

import generation.WatWriter
import generation.WebAssemblyInstruction

data class WasmMemory(val index: Index, val min: Int, val max: Int?, val shared: Boolean?) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        val sh = if(shared != null) if(shared) " shared" else "" else ""
        val mx = if(max != null) " $max" else ""
        wat.writeLine("(memory${sh} $min${mx})")
    }

}
