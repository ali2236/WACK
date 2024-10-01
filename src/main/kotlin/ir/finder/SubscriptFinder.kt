package ir.finder

import analysis.ddt.Polynomial
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.statement.Statement
import ir.statement.SymbolLoad

@Deprecated("not completed")
class SubscriptFinder : Visitor() {

    private var symbol: SymbolLoad? = null
    private var result: Polynomial? = null

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if(symbol != null) {
            when (v) {
                is BinaryOP -> {
                    if (v.left is SymbolLoad && v.left == symbol) {

                    } else if (v.right is SymbolLoad && v.right == symbol) {

                    }
                }
            }
        }
        super.visit(v, replace)
    }

    fun findSymbol(symbol: SymbolLoad, stmt: Statement) : Polynomial? {
        this.symbol = symbol
        this.result = Polynomial()
        stmt.visit(this)
        return result
    }
}