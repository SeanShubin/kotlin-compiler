package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.example.calculator.TokenCompiler.toTokenCursor
import com.seanshubin.kotlin.compiler.domain.ParseResult
import com.seanshubin.kotlin.compiler.example.calculator.ExpressionCompiler.toExpressionCursor

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {
//        complexSample()
        simpleSample()
    }

    private fun simpleSample(){
        val input = "5 + 7 - 8"
        val tokenCursor = input.toTokenCursor().filter{it !is Token.WhitespaceBlock}
        val expressionCursor = tokenCursor.toExpressionCursor()
        expressionCursor.reifyAll().forEach(::println)
    }
    private fun complexSample(){
        val input = "((1 + 2) * 3 + 8 / (3 + 1) - 1) / 5 - 1"
        val tokenCursor = input.toTokenCursor()
        tokenCursor.reifyAll().forEach(::println)
    }
}
