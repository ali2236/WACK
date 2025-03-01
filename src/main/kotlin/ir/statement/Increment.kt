package ir.statement

import generation.c.CWriter
import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

// symbol++
class Increment(var stmt: AssignmentStore, var direction: Direction) : BasicStatement(), AssignmentStore {
    override fun assignedWith(): Expression {
        return stmt.assignedWith()
    }

    override fun assignedTo(): SymbolLoad {
        return stmt.assignedTo()
    }

    override fun replaceAssign(newValue: Expression) {
        return stmt.replaceAssign(newValue)
    }

    override fun write(out: Appendable) {
        stmt.write(out)
    }

    override fun visit(v: Visitor) {
       v.visit(stmt){this.stmt = it as AssignmentStore}
    }

    override fun wat(wat: WatWriter) {
        stmt.wat(wat)
    }

    override fun c(writer: CWriter) {
        writer.inLine {
            stmt.assignedTo().c(writer)
            val s = when(direction){
                Direction.plus -> "++"
                Direction.minus -> "--"
            }
            writer.write(s)
            writer.write(";")
        }
    }

    enum class Direction {
        plus,
        minus,
    }

    companion object {
        fun plus(stmt: AssignmentStore): Statement {
            return Increment(stmt, Direction.plus)
        }

        fun minus(stmt: AssignmentStore): Statement {
            return Increment(stmt, Direction.minus)
        }
    }
}

