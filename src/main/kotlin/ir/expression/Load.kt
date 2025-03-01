package ir.expression

import compiler.WAPC
import generation.c.CWriter
import generation.wat.WatWriter
import ir.Names
import ir.finder.ExpressionFinder
import ir.finder.Visitor
import ir.statement.SymbolLoad
import ir.wasm.WasmBitSign
import ir.wasm.Index
import ir.wasm.WasmValueType

class Load(
    val type: WasmValueType,
    var address: Expression,
    val memoryIndex: Index,
    var offset: Int = 0,
    val align: Int = 0,
    val memorySize: Int? = null,
    val sign: WasmBitSign? = null,
    val atomic: Boolean = false,
) : Expression(), SymbolLoad {

    override fun write(out: Appendable) {
        if (atomic) {
            out.append("Atomic:")
        }
        out.append(Names.memory + memoryIndex)
        out.append("[")
        address.write(out)
        out.append("+$offset")
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
        val sgn = if (sign != null && memorySize != null) "${memorySize}_${sign}" else ""
        val memIndex = if (WAPC.params.multipleMemories) " $memoryIndex" else ""
        val atomic = if (atomic) ".atomic" else ""
        wat.writeLine("${type}${atomic}.load$sgn$memIndex${ofst}${algn}", this)
    }

    override fun c(writer: CWriter) {
        if (atomic) {
            throw Exception("Atomic not supported")
        }
        writer.write(Names.memory + memoryIndex)
        writer.write("[")
        address.c(writer)
        writer.write("+$offset")
        writer.write("]")
    }

    override fun clone(): Load {
        return Load(type, address.clone(), memoryIndex, offset, align)
    }

    override fun exprType(): WasmValueType {
        return type
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