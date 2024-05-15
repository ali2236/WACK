package ir.expression

import ir.statement.Assignment
import wasm.WasmValueType

class Increment(symbol: Symbol, operator: Operator = Operator.add) :
    Assignment(symbol, BinaryOP(symbol.type, operator, symbol, Value(symbol.type,"1"))) {
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