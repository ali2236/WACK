package refinment

import ir.expression.*
import ir.statement.Assignment
import ir.statement.Statement

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRefiner : Refiner() {

    override fun refineInstruction(stmt: Statement) {
        if (stmt is Assignment) {
            if (stmt.value is BinaryOP) {
                val opr = stmt.value
                if (
                    (opr.left == Value("1") || opr.right == Value("1")) &&
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

}