package analysis.ddt

import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Statement

// removes aliases from statement
class DiAlias {
    fun apply(stmt: Statement, facts: Set<DfaFact>?) : Statement {
        // check to expand aliased symbols
        when(stmt){
            is UnaryOP -> {
                val unaliasedValue = apply(stmt.value, facts) as Expression
                if (unaliasedValue != stmt.value){
                    val newUnary = stmt.clone() as UnaryOP
                    newUnary.value = unaliasedValue
                    return newUnary
                }
            }
            is BinaryOP -> {
                val unaliasedLeft = apply(stmt.left, facts) as Expression
                val unaliasedRight = apply(stmt.right, facts) as Expression
                if (unaliasedRight != stmt.right || unaliasedLeft != stmt.left){
                    val newBinOp = stmt.clone() as BinaryOP
                    newBinOp.left = unaliasedLeft
                    newBinOp.right = unaliasedRight
                    return newBinOp
                }
            }
            is Load -> {
                val unaliasedAddress = apply(stmt.address, facts) as Expression
                if (unaliasedAddress != stmt.address){
                    val newLoad = stmt.clone().also {
                        it.address = unaliasedAddress
                    }
                    return newLoad
                }
            }
            is Symbol -> {
                val alias = facts?.firstOrNull { it.symbol == stmt }
                if(alias != null && alias.value is DfaValue.Alias && alias.value.expr != null){
                    // see if the alias contains load operations
                    val loads = BreadthFirstExpressionFinder(Load::class.java, true)
                        .also { alias.value.expr.visit(it) }
                        .result()
                    if (loads.isNotEmpty()){
                        // expand
                        return alias.value.expr
                    }
                }
            }
        }
        return stmt
    }
}