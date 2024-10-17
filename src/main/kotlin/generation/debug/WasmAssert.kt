package generation.debug

import WAPC
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.statement.*
import ir.wasm.WasmValueType

object WasmAssert {
    fun equal(first: Expression, second: Expression): Statement {
        if (!WAPC.params!!.enableAsserts){
            return Empty()
        }
        return If(
            condition = BinaryOP(
                WasmValueType.i32,
                BinaryOP.Operator.neq,
                first,
                second,
            ),
            trueBody = mutableListOf(Unreachable()),
        )
    }

    fun notEqual(first: Expression, second: Expression): Statement {
        if (!WAPC.params!!.enableAsserts){
            return Empty()
        }
        return If(
            condition = BinaryOP(
                WasmValueType.i32,
                BinaryOP.Operator.eq,
                first,
                second,
            ),
            trueBody = mutableListOf(Unreachable()),
        )
    }
}