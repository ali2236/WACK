package ir.finder

import ir.statement.Loop
import ir.statement.Statement

class LoopFinder<T : Loop>(private val loopType: Class<T>, private val topLevelOnly : Boolean = false) : Visitor() {

    private val loops = mutableListOf<Replaceable<T>>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if (loopType.isInstance(v)){
            loops.add(Replaceable(v as T, replace))
            if(!topLevelOnly){
                super.visit(v, replace)
            }
        } else {
            super.visit(v, replace)
        }
    }

    fun result(): List<Replaceable<T>> {
        return loops
    }

}