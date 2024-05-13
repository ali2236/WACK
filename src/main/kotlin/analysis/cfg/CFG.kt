package analysis.cfg

import ir.statement.Program

class CFG(val module: Program, val nodes : List<CfgNode>) {
    companion object {
        fun fromModule(module: Program){

        }
    }
}