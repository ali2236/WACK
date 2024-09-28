package analysis.ddt

data class AccessPair(
    val source : Access,
    val sink : Access,
    val distanceInfo: DependenceResult,
){

/*    val direction : Int
        get() = if (distanceInfo.distance!! > 0) 1 else if(distanceInfo.distance < 0) -1 else 0*/

    //val dependencyType : DependencyType,
}