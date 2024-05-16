package ir.statement

import ir.ChildExpression
import ir.Names
import ir.expression.*
import ir.finder.Visitor
import wasm.WasmValueType

class Store(
    val type: WasmValueType,
    var data: Expression,
    var address: Expression,
    val offset: Int = 0
) : Statement {
    val load: Load
        get() = Load(type, address, offset)

    override fun write(out: Appendable) {
        out.append(Names.memory)
        //out.append("_$type")
        out.append("[")
        if (offset != 0) {
            BinaryOP(type, Operator.add, address, Value(WasmValueType.I32, offset.toString())).write(out)
        } else {
            address.write(out)
        }
        out.append("] = ")
        data.write(out)
        out.append(";\n")
    }


    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(data){data = it},
            ChildExpression(address){address = it}
        )
    }

    override fun visit(v: Visitor) {
        v.visit(address)
        v.visit(data)
    }

}