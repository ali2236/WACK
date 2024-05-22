package ir.expression

import generation.WatWriter
import wasm.WasmValueType

// return selector == 0 ? val2 : val1
class Select(val val1: Expression, val val2: Expression, val selector: Expression, val resultType: WasmValueType) :
    Expression() {
    override fun write(out: Appendable) {
        selector.write(out)
        out.append(" ? ")
        val2.write(out)
        out.append(" : ")
        val1.write(out)
    }

    override fun wat(wat: WatWriter) {
        val1.wat(wat)
        val2.wat(wat)
        selector.wat(wat)
        wat.writeLine("select (result $resultType)")
    }

}