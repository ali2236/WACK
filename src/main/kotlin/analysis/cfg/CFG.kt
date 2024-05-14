package analysis.cfg

class CFG(val nodes : List<CfgNode>) {

    fun dot(out: Appendable){
        out.append("strict digraph {\n")

        // nodes
        nodes.forEachIndexed { i, cfgNode ->
            if(!cfgNode.valid) return@forEachIndexed

            // open tag
            out.append("$i [shape=none;")
            out.append("label=<<TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\"><TR><TD BORDER=\"0\" ALIGN=\"LEFT\" COLSPAN=\"2\">")

            // label & statement
            cfgNode.label?.let {
                out.append(it.replace("<","&lt;").replace(">", "&gt;"))
                out.append("<BR ALIGN=\"LEFT\"/>")
            }
            cfgNode.statements.forEach {
                it.write(out)
                out.append("<BR ALIGN=\"LEFT\"/>")
            }

            // close tag
            out.append("</TD></TR></TABLE>>")
            out.append("]\n")
        }

        // edges
        nodes.forEachIndexed { i, cfgNode ->
            if(!cfgNode.valid) return@forEachIndexed
            cfgNode.successors.forEach { j ->
                out.append("$i -> $j\n")
            }
        }

        out.append("}\n")
    }

}