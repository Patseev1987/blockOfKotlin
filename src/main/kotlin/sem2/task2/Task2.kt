package sem2.task2

 sealed interface Exp


class Num(val value:Int):Exp
class Sum(val left:Exp,val right:Exp):Exp


fun evol( e:Exp):Int{
    return when (e){
        is Num -> e.value
        is Sum -> evol(e.left)+evol(e.right)
    }
}

fun main() {
    println(evol(Sum(Sum(Num(4),Num(5)),Num(20))))
}