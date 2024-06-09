package analysis.ddt

data class AccessPair(
    val access1 : Access,
    val access2 : Access,
    val dependencyType : DependencyType,
)