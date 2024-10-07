package transform.shift_to_multiply

import ir.expression.BinaryOP
import ir.expression.Load
import ir.expression.Value
import ir.finder.Visitor
import ir.statement.Statement
import kotlin.math.pow

class ShiftLoadReplacer : Visitor() {

    var inLoad = false

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when(v){
            is Load -> {
                inLoad = true
                super.visit(v, replace)
                inLoad = false
            }
            is BinaryOP -> {
                if (inLoad && v.operator == BinaryOP.Operator.shl && v.right is Value){
                    replace(BinaryOP(
                        type = v.type,
                        operator = BinaryOP.Operator.mul,
                        left = v.left,
                        right = Value(v.type, 2.0.pow((v.right as Value).value.toDouble()).toInt().toString())
                    ))
                } else {
                    super.visit(v, replace)
                }
            }
            else -> super.visit(v, replace)
        }
    }
}