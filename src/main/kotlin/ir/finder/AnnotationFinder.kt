package ir.finder

import ir.annotations.WackAnnotation
import ir.statement.Block
import ir.statement.Statement

class AnnotationFinder<T : WackAnnotation>(private val annotation: Class<T>) : Visitor() {
    private val blocks = mutableListOf<Replaceable<Block>>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if(v is Block && v.annotations.any { annotation.isInstance(it) }){
            blocks.add(Replaceable(v as Block, replace))
        }
        super.visit(v, replace)
    }

    fun result() : List<Replaceable<Block>>{
        return blocks
    }
}