package analysis.dfa

import analysis.cfg.CFG
import generation.DotGraph
import generation.DotSanitizer
import ir.statement.Function

class Dfa(val nodes : List<DfaNode>) : DotGraph() {
    companion object {
        fun from(function: Function, cfg: CFG) : Dfa {
            return DfaBuilder(function, cfg).build()
        }
    }

    override fun dot(out: Appendable){
        val dot = DotSanitizer(out)
        out.append("strict digraph {\n")

        // nodes
        nodes.forEachIndexed { i, dfaNode ->
            if(!dfaNode.valid) return@forEachIndexed

            // open tag
            out.append("$i [shape=")
            if(dfaNode.label == null){
                out.append("box")
            } else {
                out.append("ellipse")
            }
            out.append(";")
            out.append("label=<<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\"><TR><TD BORDER=\"0\" ALIGN=\"LEFT\" COLSPAN=\"2\">")

            // label & statement
            dfaNode.label?.let {
                dot.append(it)
                out.append("<BR ALIGN=\"LEFT\"/>")
            }
            dfaNode.statements.forEach {
                it.write(dot)
                out.append("<BR ALIGN=\"LEFT\"/>")
            }

            // close tag
            out.append("</TD></TR></TABLE>>")
            out.append("]\n")
        }

        // edges
        nodes.forEachIndexed { i, cfgNode ->
            if(!cfgNode.valid) return@forEachIndexed
            cfgNode.successors.forEach { edge ->
                out.append("$i -> ${edge.target}")
                if(edge.label!=null) out.append("[label=\"${edge.label}\"]")
                out.append("\n")
            }
        }

        out.append("}\n")
    }
}