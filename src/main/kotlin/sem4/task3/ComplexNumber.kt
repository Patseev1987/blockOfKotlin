package sem4.task3

import sem4.task3.ComplexNumber.Companion.create
import kotlin.math.sqrt
        @Throws(DivisionByZeroException::class)
        fun ComplexNumber.div( b: ComplexNumber): ComplexNumber {
            if (b.re == 0.0 && b.im == 0.0) {
                throw DivisionByZeroException()
            }

            val divisor = b.re * b.re + b.im * b.im
            return create(
                (this.re * b.re + this.im * b.im) / divisor,
                (this.im * b.re - this.re * b.im) / divisor
            )
        }

class ComplexNumber private constructor(val re: Double, val im: Double) {
    companion object {
        fun create(re: Double, im: Double): ComplexNumber {
            return ComplexNumber(re, im)
        }

        fun abs(c: ComplexNumber): Double {
            return sqrt(c.re * c.re + c.im * c.im)
        }



    }
}
