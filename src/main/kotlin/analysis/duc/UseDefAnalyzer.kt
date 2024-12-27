package analysis.duc

import analysis.dfa.Dfa
import ir.statement.Function

object UseDefAnalyzer {

    fun analyze(function: Function, dfa: Dfa): DefUseChain {

        val useDef = DefUseChain()

        dfa.pass { node ->
            node.statement?.let { stmt ->
                DefUseFinder(useDef, stmt)
            }
        }

        return useDef
    }

}