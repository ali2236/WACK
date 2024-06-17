package analysis

import analysis.cfg.CFG
import analysis.dfa.Dfa
import ir.annotations.Skip
import ir.statement.Function
import ir.statement.Program
import java.io.File

object Analysis {

    fun writeDotFiles(program: Program, name: String) {
        // intermediate outputs
        program.statements.filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                val fileName = "${name}_f" + function.functionData.index

                /// cfg
                val cfg = CFG.from(function)
                cfg.writeToFile(fileName)

                /// dfa
                val dfa = Dfa.from(function, cfg)
                dfa.writeToFile(fileName)
            }
    }
}