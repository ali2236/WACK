package analysis.ddt

import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.Finders
import ir.finder.Visitor
import ir.statement.Statement
import ir.statement.SymbolLoad

// also find <ax+c> where <x> is <symbol|load>
// sometimes <c> is nested within multiple BinOp
class AddressPolynomialFinder(val address: Expression, val scope: AccessScope, val facts: Set<DfaFact>) : Visitor() {

    private val p = Polynomial()
    private var subscript: Subscript? = null
    private var operator: BinaryOP.Operator = BinaryOP.Operator.add

    init {

        split(address).forEach { part ->
            val symbols =
                BreadthFirstExpressionFinder(SymbolLoad::class.java, true).also { it.visit(part) {} }.result().toSet()
            val symbol = when (symbols.size) {
                0 -> null
                1 -> symbols.first()
                else -> scope.loops.map { it.symbol }.toSet().intersect(symbols).firstOrNull()
            }
            if (symbol != null) {
                subscript = Subscript(symbol = symbol)
            }
            visit(part) {}
            if(subscript != null){
                p.addSubscript(subscript!!)
            }
        }
    }

    private fun split(expr: Expression): List<Expression> {
        if (expr is BinaryOP && expr.operator == BinaryOP.Operator.add) {
            return split(expr.left) + split(expr.right)
        }
        return listOf(expr)
    }

    // BFS
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is BinaryOP -> {
                when (v.operator.sign) {
                    BinaryOP.Operator.mul.sign -> {
                        operator = v.operator
                    }
                    BinaryOP.Operator.sub.sign -> {
                        visit(v.left) {}
                        operatorScope(BinaryOP.Operator.sub) {
                            visit(v.right) {}
                        }
                        return
                    }
                }
            }

            is Value -> {
                if (subscript == null) {
                    p.constant += v.toInt()
                } else {
                    val vv = v.value.toInt()
                    when(operator){
                        BinaryOP.Operator.add -> {
                            subscript!!.offset += vv
                        }
                        BinaryOP.Operator.mul -> {
                            subscript!!.multiplier *= vv
                            subscript!!.offset *= vv // maybe remove this line?
                        }
                        BinaryOP.Operator.sub -> {
                            subscript!!.offset -= vv
                        }
                    }
                }
                return
            }
        }
        super.visit(v, replace)
    }

    private fun operatorScope(newOperator: BinaryOP.Operator, scope: () -> Unit) {
        val oldOperator = operator
        operator = newOperator
        scope()
        operator = oldOperator
    }

    fun result(): Polynomial {
        return p
    }
}