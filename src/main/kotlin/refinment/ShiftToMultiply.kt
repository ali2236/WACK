package refinment

import ir.ChildExpression
import ir.expression.*
import ir.statement.Statement
import wasm.WasmValueType
import kotlin.math.pow

class ShiftToMultiply : Refiner() {

    override fun refineInstruction(stmt: Statement) {
        for (child in stmt.expressions()) {
            refineChildExpression(child)
            refineInstruction(child.statement)
        }
    }

    private fun refineChildExpression(expr: ChildExpression) {
        if (expr.statement is BinaryOP) {
            val binOp = expr.statement
            if (binOp.operator == Operator.shl && binOp.right is Value) {
                val v = binOp.right as Value
                binOp.operator = Operator.mul
                binOp.right = Value(WasmValueType.I32, 2.0.pow(v.value.toDouble()).toInt().toString())
            }
        }
    }

}