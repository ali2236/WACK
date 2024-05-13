package restructure

import ir.ChildExpression
import ir.Names
import ir.expression.ConstLoad
import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.Assignment
import ir.statement.Statement
import ir.statement.Store


// Shouldn't be used
class ConstMemoryRef : Restructure() {

    private val constRef = mutableMapOf<Value, Symbol>()
    private fun getConstRef(value: Value) : Symbol{
        if(!constRef.containsKey(value)){
            // make symbol
            val functionLocals = currentFunction.functionData.locals
            val symbol = Symbol(value.type, Names.local + "${functionLocals.size}")
            functionLocals.add(value.type)
            constRef[value] = symbol

            // push init code into function body
            currentFunction.push(Assignment(symbol, value))
        }
        return constRef[value]!!
    }

    override fun restructureInstruction(stmt: Statement) {
        for (child in stmt.expressions()) {
            refineChildExpression(child)
            restructureInstruction(child.statement)
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
}