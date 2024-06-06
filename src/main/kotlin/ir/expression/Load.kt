package ir.expression

import generation.WatWriter
import ir.Names
import ir.finder.Visitor
import ir.statement.SymbolLoad
import wasm.Index
import wasm.WasmValueType

class Load(
    val type: WasmValueType,
    var address: Expression,
    val memoryIndex: Index,
    val offset: Int = 0,
    val align: Int = 0,
) : Expression(), SymbolLoad {

    override fun write(out: Appendable) {
        out.append(Names.memory + memoryIndex)
        out.append("[")
        if (offset != 0) {
            BinaryOP(type, BinaryOP.Operator.add, address, Value(WasmValueType.i32, offset.toString())).write(out)
        } else {
            address.write(out)
        }
        out.append("]")
    }

    override fun visit(v: Visitor) {
        v.visit(address) { address = it as Expression }
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + offset
        return result
    }

    override fun wat(wat: WatWriter) {
        address.wat(wat)
        val ofst = if (offset != 0) " offset=$offset" else ""
        val algn = if (align != 0) " align=$align" else ""
        wat.writeLine("${type}.load $memoryIndex${ofst}${algn}")
    }

    override fun clone(): Load {
        return Load(type, address.clone(), memoryIndex, offset, align)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Load) return false

        if (type != other.type) return false
        if (address != other.address) return false
        if (memoryIndex != other.memoryIndex) return false
        if (offset != other.offset) return false
        if (align != other.align) return false

        return true
    }

}