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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Load) return false

        if (type != other.type) return false
        if (address != other.address) return false
        if (offset != other.offset) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + offset
        return result
    }


}