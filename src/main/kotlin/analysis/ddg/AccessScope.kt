package analysis.ddg

import ir.statement.RangeLoop
import ir.statement.Statement
import java.util.Stack

class AccessScope(loops: Stack<RangeLoop>, val parent: Statement) {

    val loops: Stack<RangeLoop>

    init {
        this.loops = loops.clone() as Stack<RangeLoop>
    }

    fun intersect(other: AccessScope): Set<RangeLoop> {
        return loops.intersect(other.loops)
    }
}