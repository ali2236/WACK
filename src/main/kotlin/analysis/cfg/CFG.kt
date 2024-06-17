package analysis.cfg

import generation.DotGraph
import generation.DotSanitizer
import ir.statement.Function

// Control Flow Graph
class CFG(val nodes: List<CfgBlock>) : DotGraph() {

    override val graphName: String = "cfg"

    companion object {
        fun from(function: Function): CFG {
            return CfgBuilder(function).build()
        }
    }

    override fun dot(out: Appendable) {
        val dot = DotSanitizer(out)
        out.append("strict digraph {\n")

        // nodes
        nodes.forEachIndexed { i, cfgNode ->
            if (!cfgNode.valid) return@forEachIndexed

            // open tag
            out.append("$i [shape=")
            if (cfgNode.label == null) {
                out.append("box")
            } else {
                out.append("ellipse")
            }
            out.append(";")
            out.append("label=<<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\"><TR><TD BORDER=\"0\" ALIGN=\"LEFT\" COLSPAN=\"2\">")

            // label & statement
            if (cfgNode.label != null) {
                cfgNode.label.let {
                    dot.append(it)
                    out.append("<BR ALIGN=\"LEFT\"/>")
                }
            } else {
                cfgNode.statements.forEach {
                    it.write(dot)
                    out.append("<BR ALIGN=\"LEFT\"/>")
                }
            }

            // close tag
            out.append("</TD></TR></TABLE>>")
            out.append("]\n")
        }

        // edges
        nodes.forEachIndexed { i, cfgNode ->
            if (!cfgNode.valid) return@forEachIndexed
            cfgNode.successors.forEach { edge ->
                out.append("$i -> ${edge.target}")
                if (edge.label != null) out.append("[label=\"${edge.label}\"]")
                out.append("\n")
            }
        }

        out.append("}\n")
    }

}



