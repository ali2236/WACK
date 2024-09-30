package analysis.ddt

import ir.statement.RangeLoop
import java.util.Stack

class AccessScope(loops: Stack<RangeLoop>) {

    val loops: Stack<RangeLoop>

    init {
        this.loops = loops.clone() as Stack<RangeLoop>
    }

    fun intersect(other: AccessScope): Set<RangeLoop> {
        return loops.intersect(other.loops)
    }
}