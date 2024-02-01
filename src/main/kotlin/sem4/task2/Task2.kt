package sem4.task2






class NotIntArgumentException(arg:String):Exception(arg)


fun sum(a:String, b:String):Int{
    try {
        return a.toInt()+b.toInt()
    }catch (e:NumberFormatException){
        throw NotIntArgumentException("Wrong data!")
    }
}
fun main(vararg args:String) {
    try{
       println( sum(args[0],args[1]))
    } catch (e:NotIntArgumentException){
        println(e.message)
    } catch (e:IndexOutOfBoundsException){
        println("element wasn't found")
    }
}