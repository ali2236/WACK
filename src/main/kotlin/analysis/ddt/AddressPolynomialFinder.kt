package analysis.ddt

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value
import ir.finder.Visitor
import ir.statement.Statement
import ir.statement.SymbolLoad

// also find <ax+c> where <x> is <symbol|load>
class AddressPolynomialFinder(address: Expression) : Visitor() {

    private val p = Polynomial()
    private var multiplier = Value.one
    private var symbol : SymbolLoad? = null
    private var sign : Long = 1L

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
                        } else if(v.right is BinaryOP && v.left is Value){
                            // multiply operation by value
                            val oldMultiplier = multiplier.clone()
                            multiplier = multiplier.multiply(v.left as Value)
                            visit(v.right){}
                            multiplier = oldMultiplier as Value
                        } else {
                            // no way
                            throw Exception("couldn't decode $v")
                        }
                    }
                    BinaryOP.Operator.sub.sign -> {
                        if(v.left is SymbolLoad && v.right is Value){
                            v.left.visit(this)
                            symbol = v.left as SymbolLoad
                            sign = sign * -1
                            v.right.visit(this)
                            symbol = null
                            sign = sign * -1
                            return
                        }
                    }
                }
            }
            is Value -> {
                if(symbol == null){
                    p.addOffset(v)
                } else {
                    p.addSymbolOffset(symbol!!, v.multiply(sign))
                }
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