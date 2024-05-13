package analysis.cfg

import ir.expression.Expression
import ir.statement.Statement

class CfgNode(
    val name: String,
    val statements: List<Statement>,
    val successors: List<CfgNode>,
) {
    companion object {
        fun from(stmt: Statement){
            // TODO: put [changesControlFlow] on [Statement] Class
            if(stmt is Expression){

            }
        }
    }
}