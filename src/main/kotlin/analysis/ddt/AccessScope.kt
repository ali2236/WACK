package analysis.ddt

import ir.statement.RangeLoop
import java.util.Stack

class AccessScope(loops: Stack<RangeLoop>) {
    val loops: Stack<RangeLoop>

    init {
        this.loops = loops.clone() as Stack<RangeLoop>
    }
}