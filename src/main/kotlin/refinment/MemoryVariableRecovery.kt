package refinment

import ir.ChildExpression
import ir.Names
import ir.expression.ConstLoad
import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.Assignment
import ir.statement.Statement
import ir.statement.Store


class MemoryVariableRecovery : Refiner() {

    private val constRef = mutableMapOf<Value, Symbol>()
    private fun getConstRef(value: Value) : Symbol{
        if(constRef.containsKey(value)){
            return constRef[value]!!
        } else {
            val symbol = Symbol(Names.constRef + "${constRef.size}")
            constRef[value] = symbol
            return symbol
        }
    }

    override fun refineInstruction(stmt: Statement) {
        for (child in stmt.expressions()) {
            refineChildExpression(child)
            refineInstruction(child.statement)
        }

        if (stmt is Store) {
            if (stmt.address is Value) {
                val address = stmt.address as Value
                val smbl = getConstRef(address)
                replaceCurrentInstruction(Assignment(smbl, stmt.data))
            }

        }
    }

    private fun refineChildExpression(expr: ChildExpression){
        if(expr.statement is Load){
            val load = expr.statement
            if(load.address is Value){
                val address = load.address as Value
                val smbl = getConstRef(address)
                expr.replace(ConstLoad(load, smbl))
            }
        }
    }

    // constant memory load
    private fun constantMemoryLoadStoreReplace(stmt: Statement){

    }
    // local variable memory load
}