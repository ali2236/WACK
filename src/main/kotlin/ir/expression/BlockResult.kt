package ir.expression

import generation.WatWriter
import ir.statement.Block
import ir.wasm.WasmValueType

class BlockResult(val type: WasmValueType, val block: Block) : Expression() {
    override fun clone(): Expression {
        return this
    }

    override fun write(out: Appendable) {
        out.append("BlockResult(${block.printHeader()})")
    }

    override fun wat(wat: WatWriter) {
        // nothing
    }
}