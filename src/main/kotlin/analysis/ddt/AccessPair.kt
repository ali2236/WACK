package analysis.ddt

data class AccessPair(
    val source : Access,
    val sink : Access,
    val dependencyType : DependencyType,
)