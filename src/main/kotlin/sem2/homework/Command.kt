package sem2.homework

sealed interface Command {
    fun isValid(): Boolean
    fun invoke()
}

//class Show(val nameForSearch:String) : Command {
//    override fun isValid(): Boolean {
//        return true
//    }
//
//    override fun invoke() {
//        if (people.isEmpty()) {
//            println("Not initialized\n")
//        } else {
//            println(people[nameForSearch])
//        }
//    }
//
//}


class Show : Command {
    override fun isValid(): Boolean {
        return true
    }

    override fun invoke() {
        if (people.isEmpty()) {
            println("Not initialized\n")
        } else {
            println(lastCommand)
        }
    }
}

class Add : Command {
    private val phoneTemplate = """\+?\d+""".toRegex()
    private val emailTemplate = """([a-zA-Z]+)@([a-zA-Z]+)\.([a-zA-Z]+)""".toRegex()
    private var name = ""
    private var emailPerson: String = ""
    private var phonePerson: String = ""
    private var answer = ""

    init {
        println(
            """Enter tne name:
            |
        """.trimMargin()
        )
        name = readln()
        println(
            """
            |Choose what do you want to enter.
            |Phone number or email and enter <e> or <p>
            |""".trimMargin()
        )
        answer = readln()
    }

    override fun isValid(): Boolean {

        when (answer) {
            "e" -> {
                println("Enter the email:\n")
                val email = readln()
                if (email.matches(emailTemplate)) {
                    emailPerson = email
                    return true
                } else {
                    println("""
                        |
                        |You entered the wrong email address!
                        | --->$email<---
                        | 
                    """.trimMargin())
                    return false
                }
            }

            "p" -> {
                println("Enter the phone:\n")
                val phone = readln()
                if (phone.matches(phoneTemplate)) {
                    phonePerson = phone
                    return true
                } else {
                    println("""
                        |
                        |You entered the wrong phone number!
                        | --->$phone<---
                        | 
                    """.trimMargin())
                    return false
                }
            }

            else -> {
                println("""
                        |
                        |You entered the wrong symbol!                  
                        | --->$answer<---
                        | 
                    """.trimMargin())
                return false
            }
        }
    }

    override fun invoke() {

        val person = if (people.containsKey(name)) people[name] else Person(name)
        if (emailPerson.isNotEmpty()) {
            person?.addEmail(emailPerson)
            lastCommand = "add $name email $emailPerson"
        }
        if (phonePerson.isNotEmpty()) {
            person?.addPhones(phonePerson)
            lastCommand = "add $name phone $phonePerson"
        }
        people[name] = person!!


    }
}

class Exit : Command {
    override fun isValid(): Boolean {
        return true
    }

    override fun invoke() {
        exit = true
    }
}

class Help : Command {
    val HELP = """You can use next commands:
         |1. Help
         |2. Add
         |3. Show
         |4. Exit
         |
         |When you entered add, you should enter name and choose what
         |do you want to enter (Phone or Email).
         |When you enter phone number you should use only numbers.
         |Phone number can start with <+>
         |When you enter email you should use that template <ewee@ttt.com>
         |
""".trimMargin()

    override fun isValid(): Boolean {
        return true
    }

    override fun invoke() {
        println(HELP + '\n')
    }
}
