package analysis.ddt

import ir.expression.Symbol
import ir.expression.Value

class Polynomial{
    private var symbolMultiplier = mutableMapOf<Symbol, Value>()
    private var offset: Value = Value.zero

    fun addOffset(v: Value){
        offset.add(v)
    }

    fun addMultiplier(symbol: Symbol, value: Value = Value.one){
        symbolMultiplier[symbol] = symbolMultiplier.getOrDefault(symbol, value).add(value)
    }

    fun symbols() : Set<Symbol>{
        return symbolMultiplier.keys
    }

    fun calculate(symbolValue: Map<Symbol, Value>): Value {
        // validate
        for (symbol in symbols()){
            if (!symbolValue.containsKey(symbol)){
                throw Exception("Value for Symbol $symbol is not provided")
            }
        }

        var sum = offset
        // calculate
        for (symbol in symbols()){
            val multiplier = symbolMultiplier[symbol]!!
            val value = symbolValue[symbol]!!
            val result = multiplier.multiply(value)
            sum = sum.add(result)
        }

        return sum
    }
}