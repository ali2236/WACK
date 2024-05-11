package refinment

import ir.expression.*
import ir.statement.Assignment
import ir.statement.Statement
import wasm.WasmValueType

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRefiner : Refiner() {

    override fun refineInstruction(stmt: Statement) {
        if (stmt is Assignment) {
            checkAssignmentStyleIncrement(stmt)
        }
    }

    private fun checkAssignmentStyleIncrement(stmt: Assignment){
        if (stmt.value is BinaryOP) {
            val opr = stmt.value as BinaryOP
            if (
                (opr.left == Value(WasmValueType.I32, "1") || opr.right == Value(WasmValueType.I32, "1")) &&
                (opr.left == stmt.symbol || opr.right == stmt.symbol)
            ){
                if (opr.operator == Operator.add){
                    replaceCurrentInstruction(Increment(stmt.symbol, Operator.add))
                } else if(opr.operator == Operator.sub){
                    replaceCurrentInstruction(Increment(stmt.symbol, Operator.sub))
                }
            }
        }
    }

}