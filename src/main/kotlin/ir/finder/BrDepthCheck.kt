package ir.finder

import ir.statement.Block
import ir.statement.Br
import ir.statement.BrIf
import ir.statement.Statement
import kotlin.math.max

class BrDepthCheck : Visitor() {

    private var maxDepth = 0
    private var depth = 0
    var outOfBoundaryJump = false

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is BrIf -> {
                if (v.depth > depth) {
                    outOfBoundaryJump = true
                }
            }

            is Br -> {
                if (v.depth > depth) {
                    outOfBoundaryJump = true
                }
            }

            is Block -> {
                increaseDepth()
                super.visit(v, replace)
                decreaseDepth()
                return
            }
        }
        super.visit(v, replace)
    }

    private fun increaseDepth() {
        depth++
        maxDepth = max(depth, maxDepth)
    }

    private fun decreaseDepth() {
        depth--
    }


}