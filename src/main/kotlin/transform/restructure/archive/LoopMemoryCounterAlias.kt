package transform.restructure.archive

import ir.finder.Replaceable
import ir.expression.*
import ir.finder.Finders
import ir.statement.*
import ir.wasm.Index
import ir.wasm.WasmScope
import transform.restructure.Restructure
import kotlin.math.max

// replaces load with constload/symbol around loops
@Deprecated("Doesn't work correctly")
class LoopMemoryCounterAlias : Restructure() {

    private val memRef = mutableMapOf<Load, Symbol>()
    private fun getMemRef(load: Load): Symbol {
        if (!memRef.containsKey(load)) {
            // make symbol
            val params = currentFunction.functionData.type.params
            val functionLocals = currentFunction.functionData.locals
            val index = Index.number(functionLocals.size + params.size)
            val symbol = Symbol(WasmScope.local, load.type, index)
            functionLocals.add(load.type)
            memRef[load] = symbol
        }
        return memRef[load]!!
    }

    override fun restructureBlock(block: Block) {
        // call every instruction in loop body
        if (block is ConditionLoop) {
            refineLoop(block)
            memRef.forEach{ (load, symbol) ->
                // pre
                val assign = Assignment(symbol, load)
                currentBlock.parent!!
                    .instructions
                    .add(max(0, currentBlock.indexInParent!!-1), assign)
                currentBlock.indexInParent = currentBlock.indexInParent!! + 1
                // post
                val store = Store(load, symbol)
                currentBlock.parent!!
                    .instructions
                    .add(currentBlock.indexInParent!!+1, store)
            }
        }

        // call every sub-block
        val subBlocks = block.instructions
            .withIndex()
            .filter { it.value is Block }
            .filterIsInstance<IndexedValue<Block>>()

        for ((i, subBlock) in subBlocks) {
            blocks.push(subBlock)
            restructureBlock(subBlock)
            blocks.pop()
        }
    }

    private fun refineLoop(loop: ConditionLoop) {
        restructureInstruction(loop.condition)
        // call every instruction
        for (i in 0 until loop.instructions.size) {
            currentInstrIndex = i
            val stmt = loop.instructions[i]
            restructureInstruction(stmt)
        }
    }

    override fun restructureInstruction(stmt: Statement) {
        for (child in Finders.expressions(stmt)) {
            refineChildExpression(child)
            restructureInstruction(child.statement)
        }
        if (stmt is Store && stmt.symbol.address is Symbol) {
            val smbl = getMemRef(stmt.symbol)
            replaceCurrentInstruction(Assignment(smbl, stmt.data))
        }
    }

    private fun refineChildExpression(expr: Replaceable<Expression>) {
        if (expr.statement is Load && expr.statement.address is Symbol) {
            val load = expr.statement
            val smbl = getMemRef(load)
            expr.replace(smbl)
        }
    }

}
