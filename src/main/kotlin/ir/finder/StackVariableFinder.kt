package ir.finder

import analysis.dfa.DfaValue
import analysis.dfa.StatementFactsFinder
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Load
import ir.statement.Statement
import ir.statement.Store
import ir.statement.SymbolLoad

class StackVariableFinder(block: Statement, val exclude: Set<SymbolLoad> /*Already private*/, val finder: StatementFactsFinder) : Visitor() {

    private val _stackVars = mutableListOf<Load>()

    init {
        block.visit(this)
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when(v){
            is Store -> {
                val load = v.symbol
                if(exclude.contains(load)){
                    return
                }

                if (load.isArrayReference()){
                    return
                }

                // check if its aliasing an array reference
                val symbols = Finders.symbols(load)
                finder.at(v)?.let { facts ->
                    val symbolFacts = symbols.mapNotNull { s -> facts.firstOrNull { f -> f.symbol == s } }
                    if (symbolFacts.any { it.value is DfaValue.Alias && it.value.expr?.isArrayReference() == true }){
                        return
                    }
                }


                _stackVars.add(load)
            }
        }
        super.visit(v, replace)
    }

    fun result() : List<Load> {
        return _stackVars
    }

}

// Array Patters:
// M0[3560+0] - might be a stack variable
// M0[L1+(L2<<2)+0] - has <<
private fun Expression.isArrayReference(): Boolean {
    return ExpressionFinder(BinaryOP::class.java)
        .also { this.visit(it) }
        .result()
        .any { it.operator == BinaryOP.Operator.shl }
}