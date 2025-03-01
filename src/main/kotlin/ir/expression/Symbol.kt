package ir.expression

import generation.c.CWriter
import generation.wat.WatWriter
import ir.Names
import ir.statement.SymbolLoad
import ir.wasm.Index
import ir.wasm.WasmScope
import ir.wasm.WasmValueType

open class Symbol(val scope: WasmScope, val type: WasmValueType, val index : Index) : ImmutableExpression(), SymbolLoad {

    override fun write(out: Appendable) {
        out.append(index.access(scope.prefix(), ""))
    }

    override fun wat(wat: WatWriter) {
        if(scope == WasmScope.global){
            wat.writeLine("${scope.name}.get ${index.access(Names.global)}", this)
        } else {
            wat.writeLine("${scope.name}.get $index", this)
        }
    }

    override fun c(writer: CWriter) {
        writer.write(index.access(scope.prefix(), ""))
    }

    override fun exprType(): WasmValueType {
        return type
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

    companion object {
        fun localI32(idx: Index): Symbol {
            return Symbol(WasmScope.local, WasmValueType.i32, idx)
        }
    }

}