package ir.expression

import generation.wat.WatWriter
import ir.finder.Visitor
import ir.statement.Block
import ir.wasm.WasmValueType

class ResultBlock(val type: WasmValueType, var block: Block) : Expression() {
    override fun clone(): Expression {
        return this
    }

    override fun exprType(): WasmValueType {
        return type
    }

    override fun write(out: Appendable) {
        out.append("{${block.printHeader()}}")
    }

    override fun wat(wat: WatWriter) {
        block.wat(wat)
    }

    override fun visit(v: Visitor) {
        v.visit(block){this.block = it as Block}
    }
}