package com.seanshubin.kotlin.compiler.example.calculator

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {
    @Test
    fun number(){
        val expression = "123"
        val expected = 123
        val actual = Calculator.eval(expression)
        assertEquals(expected, actual)
    }

    @Test
    fun plus(){
        val expression = "5 + 7"
        val expected = 12
        val actual = Calculator.eval(expression)
        assertEquals(expected, actual)
    }

    @Test
    fun minus(){
        val expression = "12 - 5"
        val expected = 7
        val actual = Calculator.eval(expression)
        assertEquals(expected, actual)
    }

    @Test
    fun chainPlusMinus(){
        val expression = "4 + 5 - 7 + 2 - 1"
        val expected = 3
        val actual = Calculator.eval(expression)
        assertEquals(expected, actual)
    }

    @Test
    fun chainMinus(){
        val expression = "12 - 2 - 3"
        val expected = 7
        val actual = Calculator.eval(expression)
        assertEquals(expected, actual)
    }
}
