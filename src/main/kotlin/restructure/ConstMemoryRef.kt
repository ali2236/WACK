package restructure

import ir.Names
import ir.expression.*
import ir.finder.Finders
import ir.finder.Replaceable
import ir.statement.Assignment
import ir.statement.Statement
import ir.statement.Store
import wasm.Index
import wasm.WasmScope


// Shouldn't be used
class ConstMemoryRef : Restructure() {

    private val constRef = mutableMapOf<Value, Symbol>()
    private fun getConstRef(value: Value) : Symbol{
        if(!constRef.containsKey(value)){
            // make symbol
            val functionLocals = currentFunction.functionData.locals
            val symbol = Symbol(WasmScope.local, value.type,  Index.next(functionLocals))
            functionLocals.add(value.type)
            constRef[value] = symbol

            // push init code into function body
            currentFunction.push(Assignment(symbol, value))
        }
        return constRef[value]!!
    }

    override fun restructureInstruction(stmt: Statement) {
        for (child in Finders.expressions(stmt)) {
            refineChildExpression(child)
            restructureInstruction(child.statement)
        }

        if (stmt is Store) {
            if (stmt.symbol.address is Value) {
                val address = stmt.symbol.address as Value
                val smbl = getConstRef(address)
                replaceCurrentInstruction(Assignment(smbl, stmt.data))
            }

        }
    }

    private fun refineChildExpression(expr: Replaceable<Expression>){
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