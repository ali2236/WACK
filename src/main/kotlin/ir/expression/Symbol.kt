package ir.expression

import generation.WatWriter
import ir.statement.SymbolLoad
import ir.wasm.Index
import ir.wasm.WasmScope
import ir.wasm.WasmValueType

open class Symbol(val scope: WasmScope, val type: WasmValueType, val index : Index) : ImmutableExpression(), SymbolLoad {

    override fun write(out: Appendable) {
        out.append("${scope.prefix()}$index")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("${scope.name}.get $index", this)
    }

    override fun getType(): List<WasmValueType> {
        return listOf(type)
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