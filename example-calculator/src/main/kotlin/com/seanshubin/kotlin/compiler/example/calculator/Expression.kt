package com.seanshubin.kotlin.compiler.example.calculator

interface Expression {
    fun eval():Int
    data class Number(val x: Int) : Expression {
        override fun toString(): String = "Number($x)"
        override fun eval(): Int = x
    }
    data class Add(val first: Expression, val second: Expression):Expression {
        override fun toString(): String = "Add)"
        override fun eval(): Int = first.eval() + second.eval()
    }
    data class Subtract(val first: Expression, val second: Expression):Expression {
        override fun toString(): String = "Subtract"
        override fun eval(): Int = first.eval() - second.eval()
    }
    data class Multiply(val first: Expression, val second: Expression):Expression {
        override fun toString(): String = "Multiply"
        override fun eval(): Int = first.eval() * second.eval()
    }
    data class Divide(val first: Expression, val second: Expression):Expression {
        override fun toString(): String = "Divide"
        override fun eval(): Int = first.eval() / second.eval()
    }
    data class Positive(val x: Expression):Expression {
        override fun toString(): String = "Positive"
        override fun eval(): Int = x.eval()
    }
    data class Negative(val x: Expression):Expression {
        override fun toString(): String = "Negative"
        override fun eval(): Int = -x.eval()
    }
}
