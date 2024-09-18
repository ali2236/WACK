package analysis.ddt

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value
import ir.finder.Visitor
import ir.statement.Statement
import ir.statement.SymbolLoad

class AddressPolynomialFinder(address: Expression) : Visitor() {

    private val p = Polynomial()

    init {
        visit(address) {}
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            // terminal nodes
            is BinaryOP -> {
                when (v.operator.sign) {
                    BinaryOP.Operator.mul.sign -> {
                        if (v.left is Value && v.right is SymbolLoad) {
                            p.addMultiplier(v.right as SymbolLoad, v.left as Value)
                            return
                        } else if (v.right is Value && v.left is SymbolLoad) {
                            p.addMultiplier(v.left as SymbolLoad, v.right as Value)
                            return
                        } else {
                            // no way
                            throw Exception("couldn't decode $v")
                        }
                    }
                }
            }

            is Value -> {
                p.addOffset(v)
                return
            }
            is SymbolLoad -> {
                p.addMultiplier(v)
                return
            }
        }
        super.visit(v, replace)
    }

    fun result(): Polynomial {
        return p
    }
}