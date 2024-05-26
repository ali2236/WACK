package analysis.dfa

import ir.expression.Expression

interface DfaValue {
    class Expr(val value: Expression) : DfaValue

    class UpperBound() : DfaValue

    class LowerBound() : DfaValue
}

