package refinment

import ast.expression.*

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRefiner : Refiner() {

    override fun refineInstruction(expr: Expression) {
        if (expr is Assignment) {
            if (expr.value is BinaryOP) {
                val opr = expr.value
                if (
                    (opr.left == Value("1") || opr.right == Value("1")) &&
                    (opr.left == expr.symbol || opr.right == expr.symbol)
                ){
                    if (opr.operator == Operator.add){
                        replaceCurrentInstruction(Increment(expr.symbol, Operator.add))
                    } else if(opr.operator == Operator.sub){
                        replaceCurrentInstruction(Increment(expr.symbol, Operator.sub))
                    }
                }
            }
        }
    }

}