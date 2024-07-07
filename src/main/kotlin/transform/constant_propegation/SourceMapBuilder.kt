package transform.constant_propegation

import ir.finder.Replaceable
import ir.finder.Visitor
import ir.statement.Statement

class SourceMapBuilder : Visitor() {
    private val srcMap = mutableMapOf<Long, Replaceable<Statement>>()
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if(v.id == null){
            throw Error("null id on $v")
        }
        srcMap.put(v.id!!, Replaceable(v, replace))
        super.visit(v, replace)
    }

    fun result() : Map<Long, Replaceable<Statement>>{
        return srcMap
    }
}