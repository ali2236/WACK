package analysis.ddg

enum class DependencyType {
    True, // read after write
    Anti, // write after read
    Flow, // data dependence
    Output, // write after write
}