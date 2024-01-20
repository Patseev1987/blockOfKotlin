package sem1.homework

const val FIRST_LINE = """You can use next commands <help>, <exit>, <add>
    |Enter a command:
"""
const val HELP = """You should enter ->
|"add <name> phone <phone>" or
|"add <name> email <email>"
|if you want to exit, you will enter <exit>
"""

val phoneTemplate = """\+?\d+""".toRegex()
val emailTemplate = """([a-zA-Z]+)@([a-zA-Z]+)\.([a-zA-Z]+)""".toRegex()

private fun enterInformation() {
    println("Enter a name")
    val name = readln()
    println(
        """What do you want to enter?
        |Phone or Email?
        |Please enter e or p
    """.trimMargin()
    )
    val choice = readln()
    when (choice) {
        "e" -> enterEmail(name)
        "p" -> enterPhone(name)
        else -> {
            println("You entered wrong symbol.\n Try again")
            return
        }
    }
}


private fun enterEmail(name: String) {
    println(
        """Enter email like this <alice@rt.com>
        |Email address should contain only letters and should be separated <@> and <.>
    """.trimMargin()
    )
    val email = readln()
    if (email.matches(emailTemplate)) {
        println("name $name email $email")
    } else {
        println("""You made a mistake in email address.
            |$email doesn't look like <alice@rt.com>
        """.trimMargin())
    }
}

private fun enterPhone(name: String) {
    println(
        """Enter phone number like this <14845415, +4545451454>
        |Phone number should contain only numbers and it can start with <+>
    """.trimMargin()
    )
    val phone = readln()
    if (phone.matches(phoneTemplate)) {
        println("name $name phone $phone")
    } else {
        println("""You made a mistake in phone number.
            |$phone - doesn't look like <14845415, +4545451454>
        """.trimMargin())
    }
}

fun main() {
    var isExit = false
    do {
        repeat(3) {
            println()
        }
        println(FIRST_LINE.trimMargin())
        var command = readln()

        when (command.lowercase()) {
            "exit" -> isExit = true
            "help" -> println(HELP.trimMargin())
            "add" -> enterInformation()
            else -> println("You entered wrong command. Try again!")
        }
    } while (!isExit)

}