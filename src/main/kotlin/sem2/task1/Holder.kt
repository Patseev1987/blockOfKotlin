package sem2.task1

class Holder (private val _number:Int){
    companion object {
        val DEFAULT_NUMBER: Int = 0

        fun createHolder(defaultNumber: Int): Holder {
        return Holder(defaultNumber)
        }
    }

}
