package sem4.task1

interface SimpleLogger {
    val prefix: String

    fun log(message: String) {
        println(prefix + message)
    }
}

class SimpleLoggerImpl(
    override val prefix: String
) : SimpleLogger {

    init {
        println("SimpleLoggerImpl created")
    }
}

class StackTraceLogger ( s:SimpleLoggerImpl):SimpleLogger by s {
    override fun log(message: String) {
        super.log(message)
        RuntimeException().apply {
stackTrace.forEachIndexed { i, s -> if(i>0)  println(s)}
        }
    }
}

val stackTraceLogger by lazy {StackTraceLogger(
    SimpleLoggerImpl("MyLogger: ")
)}

fun main() {
    println("Enter main()")
    stackTraceLogger.log("Hello world!")
    stackTraceLogger.log("Hello world!")
}