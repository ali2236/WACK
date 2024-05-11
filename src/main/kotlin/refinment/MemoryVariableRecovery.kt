package refinment

import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.Statement
import ir.statement.Store


class MemoryVariableRecovery : Refiner() {

    private val constRef = mutableMapOf<Value, Symbol>()
    private fun getConstRef(value: Value) : Symbol{
        if(constRef.containsKey(value)){
            return constRef[value]!!
        } else {
            val symbol = Symbol("cr${constRef.size}")
            constRef[value] = symbol
            return symbol
        }
    }

    override fun refineInstruction(stmt: Statement) {
        /*if(stmt is Load){

        } else if(stmt is Store){
            if(stmt.address is Value){
                val smbl = getConstRef(stmt.address)

            }
        }*/
    }

    // constant memory load
    private fun constantMemoryLoadStoreReplace(stmt: Statement){

    }
    // local variable memory load
}