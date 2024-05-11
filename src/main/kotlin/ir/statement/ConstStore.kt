package ir.statement

import ir.expression.Expression
import ir.expression.Symbol

class ConstStore(val store: Store, symbol: Symbol, value: Expression) : Assignment(symbol, value)