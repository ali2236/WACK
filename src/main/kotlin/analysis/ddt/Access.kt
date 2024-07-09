package analysis.ddt

import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.Finders
import ir.statement.SymbolLoad
import ir.wasm.WasmValueType

data class Access(
    val symbol: SymbolLoad,
    val accessType: AccessType,
    val scope: AccessScope,
    val facts: Set<DfaFact>,
) {
    fun distance(a2: Access): Int? {
        if (symbol is Load || a2.symbol is Load) {
            val s1 = symbol as Load
            val s2 = a2.symbol as Load
            // check if same memory
            if (s1.memoryIndex != s2.memoryIndex) {
                return null
            }

            // polynomials
            val p1 = this.polynomial()
            val p2 = a2.polynomial()

            // check bounds
            try {

            val b1 = this.bounds(p1)
            val b2 = a2.bounds(p2)
            if (!b1.intersect(b2)) {
                return null
            }
            } catch (e: Exception){
                // TODO: check bounds at runtime
            }

            // TODO: check gcd

            // distance
            val source = p1
            val sink = p2
            val distance = sink - source

            return distance.calculate(distance.symbols().associateWith { Value.zero }).value.toInt()
        }
        return null
    }

    fun polynomial(): Polynomial {
        if (symbol !is Load) {
            throw Exception("Polynomial Only usable on Load Symbols!")
        }
        val address = symbol.address
        val poly = AddressPolynomialFinder(address).result()

        poly.addOffset(Value(WasmValueType.i32, symbol.offset.toString()))

        return poly
    }

    fun bounds(poly: Polynomial = polynomial()): ArrayBounds {
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
                value = DfaValue.Range(Value(ss.exprType(), ss.exprType().lb()), Value(ss.exprType(), ss.exprType().ub()))
            }
            symbolsRange[s] = value
        }


        val maxFacts = symbolsRange.mapValues { BinaryOP(it.value.to.exprType(), BinaryOP.Operator.sub, it.value.to, it.value.to.exprType().value(1)) }
        val minFacts = symbolsRange.mapValues { it.value.from }

        throw Exception()

        /*val max = poly.calculate(maxFacts).value.toLong()
        val min = poly.calculate(minFacts).value.toLong()

        return ArrayBounds(min, max)*/
    }
}