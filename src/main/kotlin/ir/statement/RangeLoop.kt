package ir.statement

import analysis.dfa.DfaValue
import ir.expression.BinaryOP
import ir.expression.Symbol
import ir.finder.Visitor

class RangeLoop(
    var symbol: SymbolLoad,
    var range: DfaValue.Range,
    condition: BinaryOP,
    instructions: MutableList<Statement>
) :
    ConditionLoop(condition, instructions) {

    override fun writeHeader(out: Appendable) {
        out.append("range-loop($symbol: [${range.from}, ${range.to}])")
    }

    override fun visit(v: Visitor) {
        v.visit(symbol) { this.symbol = it as SymbolLoad }
        super.visit(v)
    }

    val conditionBinaryOP: BinaryOP
        get() = condition as BinaryOP
}