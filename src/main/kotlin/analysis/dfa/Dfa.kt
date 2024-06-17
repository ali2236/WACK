package analysis.dfa

import analysis.cfg.CFG
import generation.DotGraph
import generation.DotSanitizer
import ir.statement.Function

// Data Flow Analysis
class Dfa(val nodes : MutableList<DfaNode>) : DotGraph() {

    override val graphName: String = "dfa"

    companion object {
        fun from(function: Function, cfg: CFG? = null) : Dfa {
            return DfaBuilder.build(function, cfg ?: CFG.from(function))
        }
    }

    fun pass(pass: (DfaNode) -> Unit){
        val q = mutableListOf(nodes.first())
        val visited = Array(nodes.size) { false }
        while (q.isNotEmpty()){
            val node = q.removeFirst()
            if(visited[node.id]){
                continue
            }
            pass(node)
            visited[node.id] = true
            q.addAll(node.successors.map { nodes[it.target] })
        }
    }

    fun finder() : StatementFactsFinder{
        return StatementFactsFinder(this)
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
            if (dfaNode.label != null){
                dfaNode.label.let {
                    dot.append(it)
                    out.append("<BR ALIGN=\"LEFT\"/>")
                }
            } else {
                dfaNode.statement?.let {
                    it.write(dot)
                    out.append("<BR ALIGN=\"LEFT\"/>")
                }
            }
            dot.append("IN = {" + dfaNode.IN + "}")
            out.append("<BR ALIGN=\"LEFT\"/>")
            dot.append("GEN = {" + dfaNode.GEN +"}")
            out.append("<BR ALIGN=\"LEFT\"/>")
            dot.append("OUT = {" + dfaNode.OUT + "}")
            out.append("<BR ALIGN=\"LEFT\"/>")

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