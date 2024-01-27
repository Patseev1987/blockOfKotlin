package sem3

var a:Int? = null

var c:Int? = 22

var b:Int = 10


fun main() {
    a = b
    b = c ?: 0
}