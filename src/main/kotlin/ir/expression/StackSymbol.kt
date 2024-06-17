package ir.expression

import generation.WatWriter
import ir.statement.SymbolLoad
import ir.wasm.WasmValueType

class StackSymbol(val load: Load) : Expression(), SymbolLoad {
    override fun clone(): Expression {
        return StackSymbol(load.clone())
    }

    override fun write(out: Appendable) {
        out.append("S${load.offset}")
    }

    override fun exprType(): WasmValueType {
        return load.exprType()
    }

    override fun wat(wat: WatWriter) {
        load.wat(wat)
    }
}