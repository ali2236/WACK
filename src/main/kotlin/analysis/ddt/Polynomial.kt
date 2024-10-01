package analysis.ddt

import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.SymbolLoad

open class Polynomial {
    private var symbolMultiplier = mutableMapOf<SymbolLoad, Value>()
    private var symbolOffset = mutableMapOf<SymbolLoad, Value>()
    private var offset: Value = Value.zero

    fun addOffset(v: Value) {
        offset = offset.add(v)
    }

    fun getOffset(): Value {
        return offset
    }

    fun addSymbolOffset(symbol: SymbolLoad, value: Value = Value.zero) {
        symbolOffset[symbol] = symbolOffset.getOrDefault(symbol, Value.zero).add(value)
    }

    fun addMultiplier(symbol: SymbolLoad, value: Value = Value.one) {
        symbolMultiplier[symbol] = symbolMultiplier.getOrDefault(symbol, Value.zero).add(value)
    }

    fun symbols(): Set<SymbolLoad> {
        return symbolMultiplier.keys
    }


    fun multipliers(): List<Value> {
        return symbolMultiplier.values.toList()
    }

    fun base() : Expression {
        // symbol or constant address
        val symbol = symbolMultiplier.entries.firstOrNull { it.value == Value.one }?.key ?: offset
        return symbol as Expression
    }

    fun calculate(symbolValue: Map<SymbolLoad, Value>): Value {
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
        for(symbol in p.symbolMultiplier.keys){
            if(p.symbolMultiplier[symbol] == Value.zero){
                p.symbolMultiplier.remove(symbol)
            }
        }
        return p
    }

    override fun toString(): String {
        return symbols().joinToString("+") { symbol ->
            val multiplier = symbolMultiplier.getOrDefault(symbol, Value.one)
            val offset = symbolOffset.getOrDefault(symbol, Value.zero)
            val m = if (multiplier == Value.one) "" else "${multiplier}x"
            val o = if (offset == Value.zero) "" else if (offset.value.toInt() > 0) "+$offset" else "$offset"
            "($m$symbol$o)"
        } + "+$offset"
    }

    fun getSubscript(symbol: SymbolLoad): Subscript {
        val multiplier = symbolMultiplier.getOrDefault(symbol, Value.zero).value.toInt()
        val offset = symbolOffset.getOrDefault(symbol, Value.zero).value.toInt()
        return Subscript(multiplier, symbol, offset)
    }
}