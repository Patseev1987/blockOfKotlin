package sem3.task1



//При помощи стандартных функций-расширений для работы с
//коллекциями вывести на экран:
//1. Количество людей в городе London в возрасте от 21 до 30 лет
//включительно.
//2. Список имён без повторений для города Berlin.
//3. Список городов, в которых есть хотя бы один человек по
//имени Oliver.
data class Entry(
    val city: String,
    val name: String,
    val age: Int
)
val entries = listOf(
    Entry("London", "Liam", 28),
    Entry("Berlin", "Oliver", 24),
    Entry("Madrid", "Oliver", 45),
    Entry("Berlin", "Jerry", 29),
    Entry("London", "Oliver", 19),
    Entry("Madrid", "Kate", 16),
    Entry("Berlin", "Oliver", 34),
    Entry("London", "Kate", 25),
    Entry("Berlin", "Oliver", 23),
    Entry("Milan", "Jerry", 28),
    Entry("Paris", "Charles", 44)
)

fun main() {
    println( entries.count{it.city=="London" && it.age>=21 && it.age<=30})
    println( entries.filter{it.city=="Berlin"}.map{it.name}.distinct())
    println( entries.filter { it.name == "Oliver" }.groupBy { it.city }.mapValues { it.value.count() })
}