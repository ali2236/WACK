package ir.expression

import wasm.WasmValueType

open class Symbol(val type: WasmValueType, val symbol: String) : Expression() {
    override fun write(out: Appendable) {
        out.append(symbol)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Symbol) return false
        return symbol == other.symbol
    }

    override fun hashCode(): Int {
        return symbol.hashCode()
    }


}