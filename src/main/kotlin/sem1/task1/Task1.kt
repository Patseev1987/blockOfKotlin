package sem1.task1

fun main() {
    println("Hello world!")
    mySum(5,3)


}
fun mySum ( first: Int, second: Int){
    println("""You entered $first and $second.
Their sum equals ${first+second}""".trimIndent())
}