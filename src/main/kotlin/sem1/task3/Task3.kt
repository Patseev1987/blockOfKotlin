package sem1.task3

fun letUsDrawStars(
    numberOfStarsInLine: Int,
    numberOfLineToMidl: Int,
    numberOfStarsForIncreaseByOneStep: Int
) {
    val stringBuilder = StringBuilder()
    var starsCounter = numberOfStarsInLine
    for(line in 1 .. numberOfLineToMidl) {
        for (i in 1..starsCounter) {
            stringBuilder.append("*")
        }
            stringBuilder.append("\n")
        starsCounter+=numberOfStarsForIncreaseByOneStep
    }
    for(line in numberOfLineToMidl downTo 0) {
        for (i in starsCounter downTo 1) {
            stringBuilder.append("*")
        }
        stringBuilder.append("\n")
        starsCounter-=numberOfStarsForIncreaseByOneStep
    }

    println(stringBuilder)
}


fun main() {
    letUsDrawStars(5, 2, 2)
    letUsDrawStars(1, 3, 2)
    letUsDrawStars(1,2,4)
}

