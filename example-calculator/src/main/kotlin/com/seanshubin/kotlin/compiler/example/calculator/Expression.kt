package com.seanshubin.kotlin.compiler.example.calculator

interface Expression {
    interface Operator:Expression {
        fun operation(first:Int, second:Int):Int
    }
    data class Number(val x: Int) : Expression {
        override fun toString(): String = "Number($x)"
    }
    object Plus:Operator {
        override fun toString(): String = "Plus"
        override fun operation(first: Int, second: Int): Int = first + second
    }
    object Minus:Operator {
        override fun toString(): String = "Minus"
        override fun operation(first: Int, second: Int): Int = first - second
    }
    data class OperatorNumber(val operator:Operator, val number:Number):Expression {
        override fun toString(): String = "$operator $number"
    }
    data class OperatorNumberList(val list:List<OperatorNumber>):Expression {
        override fun toString(): String = list.joinToString(", ", "[", "]")
    }
}
