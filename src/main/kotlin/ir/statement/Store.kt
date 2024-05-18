package ir.statement

import generation.WatWriter
import ir.Names
import ir.expression.*
import ir.finder.Visitor
import wasm.WasmValueType

class Store(
    val type: WasmValueType,
    var data: Expression,
    var address: Expression,
    val offset: Int = 0,
    val align: Int = 0,
) : Statement {
    val load: Load
        get() = Load(type, address, offset)

    override fun write(out: Appendable) {
        out.append(Names.memory)
        //out.append("_$type")
        out.append("[")
        if (offset != 0) {
            BinaryOP(type, Operator.add, address, Value(WasmValueType.i32, offset.toString())).write(out)
        } else {
            address.write(out)
        }
        out.append("] = ")
        data.write(out)
        out.append(";\n")
    }

    override fun visit(v: Visitor) {
        v.visit(address){address = it as Expression}
        v.visit(data){data = it as Expression}
    }

    override fun wat(wat: WatWriter) {
        address.wat(wat)
        data.wat(wat)
        val ofst = if(offset!=0)" offset=$offset" else ""
        val algn = if(align!=0)" align=$align" else ""
        wat.writeLine("${type}.store${ofst}${algn}")
    }

}