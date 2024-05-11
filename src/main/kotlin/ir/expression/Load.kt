package ir.expression

import ir.ChildExpression
import ir.Names
import wasm.WasmValueType

class Load(val type: WasmValueType, var address: Expression, val offset: Int = 0) : Expression() {
    override fun c(out: Appendable) {
        out.append(Names.memory)
        //out.append("_${type}")
        out.append("[")
        if (offset != 0) {
            BinaryOP(Operator.add, address, Value(WasmValueType.I32, offset.toString())).c(out)
        } else {
            address.c(out)
        }
        out.append("]")
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(address){address = it}
        )
    }
}