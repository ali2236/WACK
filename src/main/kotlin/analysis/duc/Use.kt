package analysis.duc

import ir.statement.Statement

class Use(val definition: Definition?, val statement: Statement){
    override fun toString(): String {
        return definition.toString()
    }
}