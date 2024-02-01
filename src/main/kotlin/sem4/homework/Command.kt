package sem4.homework


import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.Paths


private val phoneTemplate = """\+?\d+""".toRegex()
private val emailTemplate = """([a-zA-Z]+)@([a-zA-Z]+)\.([a-zA-Z]+)""".toRegex()

sealed interface Command {
    fun isValid(): Boolean
    fun invoke()
}


class Show :Command {
   private var nameForSearch: String

    init {
        println(
            """Enter a name:
                |
            """.trimMargin()
        )
        nameForSearch = readln().trim()
    }

    override fun isValid(): Boolean {
        return if (people.containsKey(nameForSearch)) true else {
            println("Person with name $nameForSearch wasn't added by user!")
            false
        }
    }

    override fun invoke() {
        println(people[nameForSearch])
    }

}


class Add : Command {
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
                    println(
                        """
                        |
                        |You entered the wrong email address!
                        | --->$email<---
                        | 
                    """.trimMargin()
                    )
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
                    println(
                        """
                        |
                        |You entered the wrong phone number!
                        | --->$phone<---
                        | 
                    """.trimMargin()
                    )
                    return false
                }
            }

            else -> {
                println(
                    """
                        |
                        |You entered the wrong symbol!                  
                        | --->$answer<---
                        | 
                    """.trimMargin()
                )
                return false
            }
        }
    }

    override fun invoke() {

        val person = people[name] ?: Person(name)
        if (emailPerson.isNotEmpty()) {
            person.addEmail(emailPerson)
            lastCommand = "add $name email $emailPerson"
        }
        if (phonePerson.isNotEmpty()) {
            person.addPhones(phonePerson)
            lastCommand = "add $name phone $phonePerson"
        }
        people[name] = person


    }
}

data object Exit : Command {
    override fun isValid(): Boolean {
        return true
    }

    override fun invoke() {
        exit = true
    }
}

data object  Help : Command {
   private val HELP = """You can use next commands:
         |1. Help
         |2. Add
         |3. Show ---> You should enter the name!
         |4. Find ---> You should enter the phone number or email address!
         |5. Export ---> You should use (*.txt) file 
         |6. Exit
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

class Find : Command {
    private var query: String

    init {
        println(
            """Enter a phone number or email address:
                |
            """.trimMargin()
        )

        query = readln().trim()
    }

    override fun isValid(): Boolean {
        return emailTemplate.matches(query) || phoneTemplate.matches(query)
    }

    override fun invoke() {
        val result = people
            .filterValues { it.phones.contains(query) || it.phones.contains(query)}
            .map{entry -> entry.key }

        if (result.isEmpty()) {
            println("""
                |
                |--->$query<---   doesn't belong to anyone!
                |
            """.trimMargin())
        }else{
            println("""
                |
                |--->$query<--- belongs to next people:
                |$result
                |
            """.trimMargin())
        }
    }
}

class Export:Command {
    private var path:String = ""
    lateinit var file:Path
    init{
        println("""Enter the path for export:""")
        path = readln()
    }
    override fun isValid(): Boolean {
        if(!path.endsWith(".txt")) {
            println("You should use *.txt file ---> $path\n")
            return false
        }
                try {
                    if(Files.exists(Paths.get(path))) {
                        file = Paths.get(path)
                    }else{
                    file = Files.createFile(Paths.get(path))
                    }

                }catch (e:NoSuchFileException) {
                    return false

                }
        return true
    }

    override fun invoke() {
        file.toFile().writeText(getPeopleInJsonFormat().toString())
        }
    }


  private  fun getPeopleInJsonFormat():List<JsonBogdanBase>{
        val result:MutableList<JsonBogdanBase> = mutableListOf()
        people.values.forEach {
            result.add(
                json {
                    "name" to it.name
                    "emails" to jarray {
                        it.emails.forEach { +it }
                    }
                    "phones" to jarray {
                        it.phones.forEach { +it }
                    }
                }
            )
        }
        return result
}