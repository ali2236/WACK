package ir.expression

import generation.WatWriter
import ir.Names
import ir.finder.Visitor
import wasm.WasmValueType

class Load(
    val type: WasmValueType,
    var address: Expression,
    val offset: Int = 0,
    val align: Int = 0,
) : Expression() {
    override fun write(out: Appendable) {
        out.append(Names.memory)
        //out.append("_${type}")
        out.append("[")
        if (offset != 0) {
            BinaryOP(type, Operator.add, address, Value(WasmValueType.i32, offset.toString())).write(out)
        } else {
            address.write(out)
        }
        out.append("]")
    }

    override fun visit(v: Visitor) {
        v.visit(address){address = it as Expression}
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

    override fun wat(wat: WatWriter) {
        address.wat(wat)
        val ofst = if(offset!=0)" offset=$offset" else ""
        val algn = if(align!=0)" align=$align" else ""
        wat.writeLine("${type}.load${ofst}${algn}")
    }


}