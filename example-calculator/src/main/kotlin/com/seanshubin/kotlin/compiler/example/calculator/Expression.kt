package com.seanshubin.kotlin.compiler.example.calculator

interface Expression {
    fun eval():Int {
        throw UnsupportedOperationException("not implemented")
    }
    data class Number(val x: Int) : Expression {
        override fun toString(): String = "Number($x)"
        override fun eval(): Int = x
    }
    object PlusOperator:Expression{
        override fun toString(): String = "PlusOperator"
    }
    object MinusOperator:Expression{
        override fun toString(): String = "MinusOperator"
    }
    object TimesOperator:Expression{
        override fun toString(): String = "TimesOperator"
    }
    object DivideOperator:Expression{
        override fun toString(): String = "DivideOperator"
    }

    data class Add(val first: Expression, val second: Expression):Expression {
        override fun toString(): String = "Add"
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
}
