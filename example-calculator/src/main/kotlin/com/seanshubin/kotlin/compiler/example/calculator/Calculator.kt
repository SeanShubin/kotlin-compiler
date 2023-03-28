package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.example.calculator.ExpressionCompiler.toExpressionCursor
import com.seanshubin.kotlin.compiler.example.calculator.TokenCompiler.toTokenCursor
import kotlin.math.exp

object Calculator {
    fun eval(expression:String):Int{
        val tokenCursor = expression.toTokenCursor().filter{it !is Token.WhitespaceBlock}
        val expressionCursor = tokenCursor.toExpressionCursor()
        val value: Expression = expressionCursor.value ?: throw RuntimeException("Unable to evaluate '${expression}'")
        if(expressionCursor.next.value != null){
            throw RuntimeException("Too many values")
        }
        value as Expression.Number
        return value.x
    }
}
