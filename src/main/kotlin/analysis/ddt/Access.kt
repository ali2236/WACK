package analysis.ddt

import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Statement
import ir.statement.SymbolLoad
import ir.wasm.WasmValueType

data class Access(
    val symbol: SymbolLoad,
    val accessType: AccessType,
    val scope: AccessScope,
    val facts: Set<DfaFact>,
) {

    fun polynomial(): Polynomial {
        if (symbol !is Load) {
            throw Exception("Polynomial Only usable on Load Symbols!")
        }
        val address = symbol.address
        val poly = AddressPolynomialFinder(address, facts).result()

        poly.addOffset(Value(WasmValueType.i32, symbol.offset.toString()))

        return poly
    }

    fun bounds(): ArrayBounds {
        val poly = this.polynomial()
        if (symbol !is Load) {
            throw Exception("Bound Only usable on Load Symbols!")
        }

        val symbols =
            BreadthFirstExpressionFinder(SymbolLoad::class.java, true).also { it.visit(symbol.address) {} }.result()
        val symbolsRange = mutableMapOf<SymbolLoad, DfaValue.Range>()
        for (s in symbols) {
            val fact = facts.first { it.symbol == s }
            var value = fact.value
            if (value !is DfaValue.Range) {
                val ss = s as Expression
                value =
                    DfaValue.Range(Value(ss.exprType(), ss.exprType().lb()), Value(ss.exprType(), ss.exprType().ub()))
            }
            symbolsRange[s] = value
        }


        // val maxFacts = symbolsRange.mapValues { BinaryOP(it.value.to.exprType(), BinaryOP.Operator.sub, it.value.to as Value, it.value.to.exprType().value(1)) }
        val maxFacts = symbolsRange.mapValues { (it.value.to as Value).add(-1) }
        val minFacts = symbolsRange.mapValues { it.value.from as Value }


        val max = poly.calculate(maxFacts).value.toLong()
        val min = poly.calculate(minFacts).value.toLong()

        return ArrayBounds(min, max)
    }

    override fun toString(): String {
        return "$accessType: $symbol"
    }
}