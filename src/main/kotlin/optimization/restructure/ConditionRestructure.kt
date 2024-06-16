package optimization.restructure

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Value
import ir.statement.If
import ir.statement.Statement
import ir.wasm.WasmValueType
import java.lang.Exception

class ConditionRestructure : Restructure() {
    override fun restructureInstruction(stmt: Statement) {
        if (stmt is If) {
            try {
                stmt.condition = simplifyCondition(stmt.condition)
            } catch (e: Exception) {
            }
        }
    }

    private fun simplifyCondition(condition: Expression): Expression {
        when (condition) {
            is BinaryOP -> {
                // invert eqz
                if (condition.operator == BinaryOP.Operator.eq
                    && condition.right == Value.zero
                    && condition.left is BinaryOP
                    && (condition.left as BinaryOP).operator.boolean
                ) {
                    val innerCondition = condition.left.clone() as BinaryOP
                    val modified = BinaryOP(
                        innerCondition.type,
                        innerCondition.operator.invert(),
                        innerCondition.left,
                        innerCondition.right
                    )

                    return modified
                }
                // simplify Ax+C < D  -> x < eval((D-C)/A)
            }
        }
        return condition
    }
}