package sem1.task2

fun main() {
    println("sumAll = ${sumAll(1, 5, 20)}")
    println("sumAll = ${sumAll()}")
    println("sumAll = ${sumAll(2, 3, 4, 5, 6, 7)}")
    println(createOutputString("Alice"))
    println(createOutputString("Bob", 23))
    println(createOutputString(isStudent = true, name = "Carol", age = 19))
    println(createOutputString("Daniel", 32, isStudent = null))
    println(multiplyBy(null, 4))
    println(multiplyBy(3, 4))
}

fun sumAll(vararg numbers: Int): Int =  numbers.sum()


fun createOutputString(name: String, age: Int = 42, isStudent: Boolean? = null): String =
     when (isStudent) {
         true -> "student ${name} has age ${age}"
         else -> "${name} has age ${age}"
     }

    fun multiplyBy(first:Int?, second:Int):Int? =
        when (first){
            null -> null
            else -> first*second
        }
