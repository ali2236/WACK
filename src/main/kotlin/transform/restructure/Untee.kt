package transform.restructure

import ir.expression.Tee
import ir.statement.Assignment
import ir.statement.Block
import ir.statement.Statement

class UnTeeRestructure : Restructure() {

    override fun restructureInstruction(stmt: Statement) {
        if (stmt is Tee) {
            currentBlock.instructions[currentInstrIndex!!] = Assignment(stmt.symbol, stmt.value)
            currentBlock.instructions[currentInstrIndex!!+1] = stmt.symbol
        }
    }
}