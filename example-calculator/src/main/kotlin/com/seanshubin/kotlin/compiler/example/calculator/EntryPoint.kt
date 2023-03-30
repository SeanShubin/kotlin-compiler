package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.example.calculator.TokenCompiler.toTokenCursor
import com.seanshubin.kotlin.compiler.domain.ParseResult
import com.seanshubin.kotlin.compiler.example.calculator.ExpressionCompiler.toExpressionCursor

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {
        complexSample()
//        simpleSample()
    }

    private fun simpleSample(){
        val input = "5 + 7 - 8"
        val tokenCursor = input.toTokenCursor().filter{it !is Token.WhitespaceBlock}
        val parser = ExpressionTopParser
        val result = parser.parse("expression", tokenCursor)
        result as ParseResult.Success
        result.tree.toLines().forEach(::println)
        println(result)
//        val expressionCursor = tokenCursor.toExpressionCursor()
//        expressionCursor.reifyAll().forEach(::println)
    }
    private fun complexSample(){
        val input = "((1 + 2) * 3 + 8 / (3 + 1) - 1) / 5 - 1"
        val tokenCursor = input.toTokenCursor().filter { it !is Token.WhitespaceBlock }
        tokenCursor.reifyAll().forEach(::println)
        val parser = ExpressionTopParser
        val result = parser.parse("expression", input)
        when(result){
            is ParseResult.Success -> {
                result.tree.toLines().forEach(::println)
            }
            is ParseResult.Failure ->{
                result.messageWithHistory().forEach(::println)
            }
        }
    }
}
