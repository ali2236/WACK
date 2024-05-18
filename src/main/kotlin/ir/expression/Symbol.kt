package ir.expression

import generation.WatWriter
import wasm.Index
import wasm.WasmScope
import wasm.WasmValueType

open class Symbol(val scope: WasmScope, val type: WasmValueType, val index : Index) : Expression() {
    override fun write(out: Appendable) {
        out.append("${scope.name[0]}$index")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("${scope.name}.get $index")
    }

}