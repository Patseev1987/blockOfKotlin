package sem2.homework

data class Person(val name: String) {

    val emails: MutableSet<String> = LinkedHashSet()
    val phones: MutableSet<String> = LinkedHashSet()
    override fun toString(): String {
        return """$name has next phones
                |${phones}
                |and next emails
                |${emails}
            """.trimMargin()
    }

    fun addEmail(email :String)=emails.add(email)
    fun addPhones(phone :String)=phones.add(phone)



}