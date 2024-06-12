package analysis.ddt

data class AccessPair(
    val source : Access,
    val sink : Access,
    val distance: Int,
){
    val direction : Int
        get() = if (distance > 0) 1 else if(distance < 0) -1 else 0

    //val dependencyType : DependencyType,
}