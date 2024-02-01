package sem4.homework

open class JsonBogdanBase {
    val values: MutableList<String> = mutableListOf()

    operator fun String.unaryPlus() {
        values.add(this)
    }

    infix fun String.to(value: String) {
        values.add(""""$this" : "$value"""")
    }

    infix fun String.to(value: JsonBogdanArray) {
        values.add(""""$this" : ${value}""")
    }


    fun jarray(block: JsonBogdanBase.() -> Unit): JsonBogdanArray {
        return JsonBogdanArray().apply(block)
    }

    override fun toString(): String {
        return values.joinToString(prefix = "{", postfix = "}", separator = ",")
    }


}


class JsonBogdanArray : JsonBogdanBase() {
    override fun toString(): String {
        return values.joinToString(prefix = "[", postfix = "]", separator = ",") { s -> """"$s"""" }
    }
}

fun json(block: JsonBogdanBase.() -> Unit): JsonBogdanBase {
    return JsonBogdanBase().apply(block)
}
