package analysis.ddt

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value
import ir.finder.Visitor
import ir.statement.Statement

class AddressPolynomialFinder(address: Expression) : Visitor() {

    private val p = Polynomial()

    init {
        visit(address){}
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when(v){
            is BinaryOP -> {
                when(v.operator.sign){
                    BinaryOP.Operator.mul.sign -> {
                        if(v.left is Value && v.right is Symbol){
                            p.addMultiplier(v.right as Symbol, v.left as Value)
                        } else if(v.right is Value && v.left is Symbol) {
                            p.addMultiplier(v.left as Symbol, v.right as Value)
                        } else {
                            // no way
                            throw Exception("couldn't decode $v")
                        }
                    }
                    BinaryOP.Operator.add.sign -> {
                        if(v.left is Value){
                            p.addOffset(v.left as Value)
                        }
                        if(v.right is Value){
                            p.addOffset(v.right as Value)
                        }
                    }
                    else -> throw Exception()
                }
            }
        }
        super.visit(v, replace)
    }

    fun result(): Polynomial{
        return p
    }
}