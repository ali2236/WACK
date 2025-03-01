package analysis.ddt.tests

import analysis.ddg.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import ir.expression.BinaryOP
import ir.expression.Value
import ir.statement.Statement
import ir.wasm.WasmValueType

@Deprecated("implementation not complete")
class RuntimeBoundTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val p1 = a1.polynomial()
        val p2 = a2.polynomial()
        // TODO: check bounds at runtime
        // 1. find the base
        val base1 = p1.baseOrOffset()
        val base2 = p2.baseOrOffset()
        // 2. add it to the offset to get linear memory array base
        val linearBase1 = BinaryOP.plus(base1, Value.i32(p1.constant))
        val linearBase2 = BinaryOP.plus(base2, Value.i32(p2.constant))
        // check_bounds(A, C)
        // 3. check if access do not cross each other's bounds
        // A. A != C
        val conditions = mutableListOf<Statement>()
        conditions.add(BinaryOP(WasmValueType.i32, BinaryOP.Operator.neq, linearBase1, linearBase2))
        // B. if A.max < C.min or A.min > C.min
        return DependenceResult(mapOf())
    }
}