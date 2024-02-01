package sem4.task3

import java.util.*
 import sem4.task3.ComplexNumber

fun main() {
        val scanner: Scanner = Scanner(System.`in`)
        println("Enter first complex number")
        val a = ComplexNumber.create(
            scanner.nextDouble(), scanner.nextDouble()
        )
        println("Enter second complex number")
        val b = ComplexNumber.create(
            scanner.nextDouble(), scanner.nextDouble()
        )

        val c: ComplexNumber
        try {
            c = a.div(b)
        } catch (e: DivisionByZeroException) {
            println("Cannot divide by zero!")
            return
        }

        val abs = ComplexNumber.abs(c)
        println(
            "first / second = " + c.re + " + " + c.im + "i"
        )
        println("|first / second| = $abs")
    }