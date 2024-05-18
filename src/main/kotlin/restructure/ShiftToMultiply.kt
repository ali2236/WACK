package restructure

import ir.finder.Replaceable
import ir.expression.*
import ir.finder.Finders
import ir.statement.Statement
import wasm.WasmValueType
import kotlin.math.pow

class ShiftToMultiply : Restructure() {

    override fun restructureInstruction(stmt: Statement) {
        for (child in Finders.expressions(stmt)) {
            refineChildExpression(child)
            restructureInstruction(child.statement)
        }
    }

    private fun refineChildExpression(expr: Replaceable<Expression>) {
        if (expr.statement is BinaryOP) {
            val binOp = expr.statement
            if (binOp.operator == Operator.shl && binOp.right is Value) {
                val v = binOp.right as Value
                binOp.operator = Operator.mul
                binOp.right = Value(WasmValueType.i32, 2.0.pow(v.value.toDouble()).toInt().toString())
            }
        }
    }

}