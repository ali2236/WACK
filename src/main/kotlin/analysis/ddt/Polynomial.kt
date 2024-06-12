package analysis.ddt

import ir.expression.Symbol
import ir.expression.Value

open class Polynomial {
    private var symbolMultiplier = mutableMapOf<Symbol, Value>()
    private var offset: Value = Value.zero

    fun addOffset(v: Value) {
        offset.add(v)
    }

    fun getOffset(): Value {
        return offset
    }

    fun addMultiplier(symbol: Symbol, value: Value = Value.one) {
        symbolMultiplier[symbol] = symbolMultiplier.getOrDefault(symbol, value).add(value)
    }

    fun symbols(): Set<Symbol> {
        return symbolMultiplier.keys
    }

    fun calculate(symbolValue: Map<Symbol, Value>): Value {
        // validate
        for (symbol in symbols()) {
            if (!symbolValue.containsKey(symbol)) {
                throw Exception("Value for Symbol $symbol is not provided")
            }
        }

        var sum = offset
        // calculate
        for (symbol in symbols()) {
            val multiplier = symbolMultiplier[symbol]!!
            val value = symbolValue[symbol]!!
            val result = multiplier.multiply(value)
            sum = sum.add(result)
        }

        return sum
    }

    operator fun minus(other: Polynomial): Polynomial {
        val p = Polynomial()
        p.addOffset(offset.add(other.offset.multiply(-1)))
        // copy
        for (symbol in symbols()) {
            p.addMultiplier(symbol, symbolMultiplier[symbol]!!)
        }
        // deduct
        for (symbol in other.symbols()) {
            p.symbolMultiplier[symbol] =
                p.symbolMultiplier
                    .getOrDefault(symbol, Value.zero)
                    .add(other.symbolMultiplier[symbol]!!.multiply(-1))
        }
        return p
    }
}