package ir.statement

import ir.ChildExpression
import ir.Names
import ir.expression.*
import wasm.WasmValueType

class Store(val type: WasmValueType, var data: Expression, var address: Expression, val offset: Int = 0) : Statement {
    override fun c(out: Appendable) {
        out.append(Names.memory)
        //out.append("_$type")
        out.append("[")
        if (offset != 0) {
            BinaryOP(Operator.add, address, Value(WasmValueType.I32, offset.toString())).c(out)
        } else {
            address.c(out)
        }
        out.append("] = ")
        data.c(out)
        out.append(";\n")
    }

    override fun symbols(): List<Symbol> {
        return address.symbols() + data.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(data){data = it},
            ChildExpression(address){address = it}
        )
    }

}