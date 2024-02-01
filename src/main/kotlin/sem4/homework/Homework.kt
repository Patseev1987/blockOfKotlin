package sem4.homework

import java.util.LinkedHashMap


var exit = false
val people: MutableMap<String,Person> = LinkedHashMap()
lateinit var lastCommand:String

fun readCommand(commandName: String): Command {
    return when (commandName.lowercase()) {
        "exit"   -> Exit
        "help"   -> Help
        "add"    -> Add()
        "show"   -> Show()
        "find"   -> Find()
        "export" -> Export()
        else -> Help
    }
}

fun main() {
    while (!exit) {
        println(
            """Enter a command:
        |If you don't know any command you can enter command <help>!
        | 
    """.trimMargin()
        )
        val commandName = readln().trim()
        println(
            """You entered:
        |$commandName
        |
    """.trimMargin()
        )
        val command = readCommand(commandName)
       if (command.isValid()) {
            command.invoke()
        } else {
            Help.invoke()
        }
    }
}