package ir.finder

import ir.statement.Loop
import ir.statement.Statement

class LoopFinder(private val topLevelOnly : Boolean = false) : Visitor() {

    private val loops = mutableListOf<Replaceable<Loop>>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if (v is Loop){
            loops.add(Replaceable(v, replace))
            if(!topLevelOnly){
                super.visit(v, replace)
            }
        } else {
            super.visit(v, replace)
        }
    }

    fun result(): List<Replaceable<Loop>> {
        return loops
    }

}