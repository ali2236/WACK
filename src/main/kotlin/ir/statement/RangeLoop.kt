package ir.statement

import analysis.dfa.DfaValue
import ir.expression.BinaryOP
import ir.expression.Symbol

class RangeLoop(val symbol : SymbolLoad, var range: DfaValue.Range, condition: BinaryOP, instructions: MutableList<Statement>) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("range-loop($symbol: [${range.from}, ${range.to}))")
    }
}