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
    private var multiplier = Value.one

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
                        } else if(v.left is BinaryOP && v.right is Value){
                            // multiply operation by value
                            val oldMultiplier = multiplier.clone()
                            multiplier = multiplier.multiply(v.right as Value)
                            visit(v.left){}
                            multiplier = oldMultiplier as Value
                        } else {
                            // no way
                            throw Exception("couldn't decode $v")
                        }
                    }
                    BinaryOP.Operator.sub.sign -> {
                        v.left.visit(this)
                        val oldMultiplier = multiplier.clone() as Value
                        multiplier = multiplier.multiply(-1)
                        v.right.visit(this)
                        multiplier = oldMultiplier
                        return
                    }
                }
            }
            is Value -> {
                p.addOffset(v.multiply(multiplier))
                return
            }
            is SymbolLoad -> {
                p.addMultiplier(v, multiplier)
                return
            }
        }
        super.visit(v, replace)
    }

    fun result(): Polynomial {
        return p
    }
}