package ir.expression

import ir.statement.Assignment
import wasm.WasmValueType

class Increment(symbol: Symbol, operator: Operator = Operator.add) :
    Assignment(symbol, BinaryOP(operator, symbol, Value(WasmValueType.I32,"1"))) {
    override fun write(out: Appendable) {
        symbol.write(out)
        val op = when ((value as BinaryOP).operator) {
            Operator.add -> "++"
            Operator.sub -> "--"
            else -> throw Error()
        }
        out.append(op)
        if(!inline){
            out.append(";\n")
        }
    }
}