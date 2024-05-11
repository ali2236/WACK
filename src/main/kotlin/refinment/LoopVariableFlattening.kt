package refinment

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol
import ir.statement.*
import java.util.Stack

class LoopVariableFlattening : Refiner(){
    private val inserts = Stack<IndexedValue<Statement>>()
    private val symbolAssignment = mutableMapOf<Symbol, Expression>()
    private fun getSymbolAssignment(symbol: Symbol): Expression? {
        return symbolAssignment[symbol]
    }

    private fun setSymbolAssignment(symbol: Symbol, value: Expression){
        symbolAssignment[symbol] = value
    }

    private fun symbolAssignmentExists(symbol: Symbol) : Boolean{
        return symbolAssignment.containsKey(symbol)
    }

    override fun refineBlock(block: Block) {
        // call every instruction in loop body
        if(block is ForLoop){
            refineForLoop(block)
            while (inserts.isNotEmpty()){
                val (index, expression) = inserts.pop()
                block.instructions.add(index, expression)
            }
        }

        // call every sub-block
        val subBlocks = block.instructions
            .withIndex()
            .filter { it.value is Block }
            .filterIsInstance<IndexedValue<Block>>()

        for ((i, subBlock) in subBlocks) {
            blocks.push(subBlock)
            refineBlock(subBlock)
            blocks.pop()
        }
    }

    private fun refineForLoop(loop: ForLoop){
        // call every instruction
        for (i in 0 until loop.instructions.size) {
            currentInstrIndex = i
            val stmt = loop.instructions[i]
            refineInstruction(stmt)
        }
    }

    override fun refineInstruction(stmt: Statement) {
        if (stmt is Assignment) {
            val smbl = stmt.symbol

            getSymbolAssignment(smbl)?.let {oldValue ->
                inserts.push(IndexedValue(currentInstrIndex!!, Assignment(smbl, oldValue)))
            }

            setSymbolAssignment(smbl, stmt.value)
            replaceCurrentInstruction(Nop())
        }

        for (child in stmt.expressions()) {
            refineChildExpression(child)
            refineInstruction(child.statement)
        }
    }

    private fun refineChildExpression(expr: ChildExpression) {
        if (expr.statement is Symbol){
            val replacement = getSymbolAssignment(expr.statement)
            if(replacement != null){
                expr.replace(replacement)
            }
        }
    }
}