package transform

import compiler.WAPC
import analysis.dfa.DfaValue
import ir.annotations.Normalized
import ir.annotations.Skip
import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ReverseVisitor
import ir.finder.Visitor
import ir.statement.*
import ir.statement.Function

class LoopNormalization : Transformer {
    override fun apply(program: Program) {
        if (!WAPC.params.normalizeLoops) {
            return
        }
        program.statements.filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }
            .flatMap { function ->
                BreadthFirstExpressionFinder(RangeLoop::class.java, false)
                    .also { function.visit(it) }
                    .result()
            }.filterNot { loop -> loop.hasAnnotation(Skip::class.java) }
            .forEach { loop -> normalizeLoop(loop) }
    }

    // if loop range doesn't start from 0:
//  if L > 0 => 0 < i < U-L
//  or increment is not ++
// then replace symbol with <increment*symbol+offset>
    private fun normalizeLoop(loop: RangeLoop) {
        var normalized = false
        val increment = BreadthFirstExpressionFinder(Increment::class.java).also { loop.visit(it) }.result().first()
        if (increment.direction == Increment.Direction.minus && loop.range.to is Value) {
            normalizeLoopDirection(loop)
            normalized = true
        }

        if (loop.range.from is Value && loop.range.from != Value.zero) {
            normalizeLoopStart(loop)
            normalized = true
        }

        if (normalized) {
            loop.annotations.add(Normalized())
        }
    }

    private fun normalizeLoopDirection(loop: RangeLoop) {
        val symbol = loop.symbol
        val from = loop.range.from
        val to = loop.range.to
        val symbolType = when (symbol) {
            is Symbol -> symbol.exprType()
            is Load -> symbol.exprType()
            else -> throw Error("Invalid Loop Symbol Type!")
        }
        // replacements
        lateinit var newFrom: Expression

        // normalize loop direction:
        // 1. replace <symbol> = <from> with <symbol> = <to>
        val rv = ReverseVisitor()
        rv.searchInParentBlocks(loop, object : Visitor() {
            override fun visit(v: Statement, replace: (Statement) -> Unit) {
                when (v) {
                    is AssignmentStore -> {
                        if (v.assignedTo() == symbol) {
                            newFrom = loop.range.to
                            v.replaceAssign(newFrom)
                            rv.stop = true
                        }
                    }
                }
            }
        })
        // 2. invert condition from <symbol> >= <to> --> <symbol> < <from>
        // 3. in loop body replace <symbol> with (<to> + <from>) - <symbol>
        // 3.5. in loop body change increment from -- to ++
        val cv = object : Visitor() {
            override fun visit(v: Statement, replace: (Statement) -> Unit) {
                if (v is If && v.condition == loop.condition) {
                    // 2
                    val c = loop.conditionBinaryOP
                    val newCondition = BinaryOP(c.type, c.operator.reverse(), c.left, from)
                    v.condition = newCondition
                    loop.condition = newCondition
                    super.visit(v.instructions) { i, stmt -> v.instructions[i] = stmt }
                    return
                } else if (v is Increment && v.stmt.assignedTo() == loop.symbol) {
                    // 3.5
                    v.stmt.replaceAssign(BinaryOP.plus(v.stmt.assignedTo() as Expression, Value.one))
                    v.direction = Increment.Direction.plus
                    return
                } else if (v == symbol) {
                    // 3
                    val substitute = BinaryOP(
                        symbolType,
                        BinaryOP.Operator.sub,
                        BinaryOP(
                            from.exprType(),
                            BinaryOP.Operator.add,
                            from,
                            to,
                        ), // TODO: cast to symbol type
                        v as Expression,
                    )
                    replace(substitute)
                } else if (v is StackExpression) {
                    return
                }
                super.visit(v, replace)
            }
        }
        cv.visit(loop.instructions) { i, stmt -> loop.instructions[i] = stmt }

        // 4. update loop metadata
        loop.range = DfaValue.Range(newFrom, loop.conditionBinaryOP.endInclusive(Increment.Direction.plus))
    }

    private fun normalizeLoopStart(loop: RangeLoop) {
        val symbol = loop.symbol
        val from = loop.range.from
        val symbolType = when (symbol) {
            is Symbol -> symbol.exprType()
            is Load -> symbol.exprType()
            else -> throw Error("Invalid Loop Symbol Type!")
        }
        // replacements
        lateinit var newFrom: Expression
        var newTo: Expression?
        // replace loop range:
        // 1. before loop body: replace <symbol> = <from> with <symbol> = 0
        val rv = ReverseVisitor()
        rv.searchInParentBlocks(loop, object : Visitor() {
            override fun visit(v: Statement, replace: (Statement) -> Unit) {
                when (v) {
                    is AssignmentStore -> {
                        if (v.assignedTo() == symbol) {
                            newFrom = Value(symbolType, "0")
                            v.replaceAssign(newFrom)
                            rv.stop = true
                        }
                    }
                }
            }
        })

        // 2. in loop condition: replace <symbol> < <to> with <symbol> < <to> - <from>
        val conditionVisitor = object : Visitor() {
            override fun visit(v: Statement, replace: (Statement) -> Unit) {
                if (v is BinaryOP && v.right is Value && from is Value) {
                    newTo = (v.right as Value).add(from.multiply(-1))
                    v.right = newTo!!
                } else if (v is BinaryOP) {
                    newTo = BinaryOP(
                        v.exprType(),
                        BinaryOP.Operator.sub,
                        v.right,
                        from,
                    )
                    v.right = newTo!!
                }
            }
        }
        conditionVisitor.visit(loop.condition) {}

        // 3. in loop body: replace <symbol> with <symbol>+<from>
        val cv = object : Visitor() {
            override fun visit(v: Statement, replace: (Statement) -> Unit) {
                if (v is If && v.condition == loop.condition) {
                    super.visit(v.instructions) { i, stmt -> v.instructions[i] = stmt }
                    return
                } else if (v is Increment) {
                    return
                } else if (v is StackExpression) {
                    return
                }
                if (v == symbol) {
                    val replacement = BinaryOP(
                        symbolType,
                        BinaryOP.Operator.add,
                        v as Expression,
                        from,
                    )
                    replace(replacement)
                }
                super.visit(v, replace)
            }
        }
        cv.visit(loop.instructions) { i, stmt -> loop.instructions[i] = stmt }

        // 4. in loop metadata: replace loop-range with new loop-range
        loop.range = DfaValue.Range(newFrom, loop.conditionBinaryOP.endInclusive(Increment.Direction.plus))
    }
}