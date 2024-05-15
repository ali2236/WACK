package analysis.cfg

class CFG(val nodes : List<CfgBlock>) {

    fun dot(out: Appendable){
        val dot = DotSanitizer(out)
        out.append("strict digraph {\n")

        // nodes
        nodes.forEachIndexed { i, cfgNode ->
            if(!cfgNode.valid) return@forEachIndexed

            // open tag
            out.append("$i [shape=")
            if(cfgNode.label == null){
                out.append("box")
            } else {
                out.append("ellipse")
            }
            out.append(";")
            out.append("label=<<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\"><TR><TD BORDER=\"0\" ALIGN=\"LEFT\" COLSPAN=\"2\">")

            // label & statement
            cfgNode.label?.let {
                dot.append(it)
                out.append("<BR ALIGN=\"LEFT\"/>")
            }
            cfgNode.statements.forEach {
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

class DotSanitizer(private val appendable: java.lang.Appendable) : java.lang.Appendable {

    override fun append(csq: CharSequence): java.lang.Appendable {
        return appendable.append(
            csq.replace(Regex("<"), "&lt;")
                .replace(Regex(">"), "&gt;")
        )
    }

    override fun append(csq: CharSequence, start: Int, end: Int): java.lang.Appendable {
        return appendable.append(
            csq.replace(Regex("<"), "&lt;")
                .replace(Regex(">"), "&gt;"), start, end
        )
    }

    override fun append(c: Char): java.lang.Appendable {
        return when (c) {
            '<' -> appendable.append("&lt;")
            '>' -> appendable.append("&gt;")
            else -> appendable.append(c)
        }
    }

}

