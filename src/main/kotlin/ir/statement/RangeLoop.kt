package ir.statement

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value

class RangeLoop(val symbol : Symbol, var from: Value, var toExclusive: Value, condition: BinaryOP, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("range-loop($symbol: [$from, $toExclusive))")
    }
}