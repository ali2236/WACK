package ir.expression

import generation.WatWriter
import ir.statement.Assignable
import wasm.Index
import wasm.WasmScope
import wasm.WasmValueType

open class Symbol(val scope: WasmScope, val type: WasmValueType, val index : Index) : Expression(), Assignable {
    override fun write(out: Appendable) {
        out.append("${scope}$index")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("${scope.name}.get $index")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Symbol) return false

        if (scope != other.scope) return false
        if (type != other.type) return false
        if (index != other.index) return false

        return true
    }

    override fun hashCode(): Int {
        var result = scope.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + index.hashCode()
        return result
    }

}