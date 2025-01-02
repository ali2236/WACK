package analysis.ddt

import ir.expression.Expression
import ir.expression.Value
import ir.statement.SymbolLoad
import kotlin.math.abs

open class Polynomial(
    val subscripts: MutableList<Subscript> = mutableListOf<Subscript>(),
    var constant: Int = 0
) {

    fun symbols(): Set<SymbolLoad> {
        return subscripts.map { it.symbol }.toSet()
    }

    fun getSubscript(symbol: SymbolLoad): Subscript {
        val ss = subscripts.filter { it.symbol == symbol }

        if (ss.isEmpty()){
            return Subscript(0, symbol)
        }

        return ss.reduce { acc, s ->
            acc.offset += s.offset
            acc.multiplier += s.multiplier
            acc
        }
    }

    fun addSubscript(subscript: Subscript) {
        subscripts.add(subscript)
    }

    // symbol
    fun base(): Expression? {
        return subscripts.firstOrNull { it.multiplier == 1 }?.symbol as Expression?
    }

    // symbol or constant address
    fun baseOrOffset(): Expression {
        return base() ?: Value.i32(constant)
    }

    fun calculate(symbolValue: Map<SymbolLoad, Value>): Long {
        // validate
        for (symbol in symbols()) {
            if (!symbolValue.containsKey(symbol)) {
                throw Exception("Value for Symbol $symbol is not provided")
            }
        }

        var sum = constant.toLong()
        // calculate
        for (subscript in subscripts) {
            val multiplier = subscript.multiplier
            val value = symbolValue[subscript.symbol]!!.value.toInt()
            sum += multiplier * value
        }

        return sum
    }

    operator fun minus(other: Polynomial): Polynomial {
        val s1 = subscripts
        val s2 = other.subscripts
        val s3 = mutableListOf<Subscript>()
        var offset = 0

        for (sub in s1 + s2) {
            val sub2 = s3.firstOrNull { it.symbol == sub.symbol }
            if (sub2 == null) {
                // if new add
                s3.add(sub)
            } else {
                // else deduct
                val sub3 = Subscript(
                    abs(sub.multiplier - sub2.multiplier),
                    sub.symbol,
                    abs(sub.offset - sub2.offset)
                )
                s3.remove(sub2)
                if(sub3.multiplier != 0){
                    s3.add(sub3)
                } else {
                    offset += sub3.offset
                }
            }
        }

        return Polynomial(s3, abs(constant - other.constant) + offset)
    }

    override fun toString(): String {
        return subscripts.joinToString("+") { s -> s.toString() } + "+$constant"
    }
}