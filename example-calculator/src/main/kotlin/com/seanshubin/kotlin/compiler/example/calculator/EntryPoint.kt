package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.example.calculator.TokenCompiler.toTokenCursor

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {
//        complexSample()
        simpleSample()
    }

    private fun simpleSample(){
        val input = "1 + 2 - 3"
        val tokenCursor = input.toTokenCursor()
        tokenCursor.reifyAll().forEach(::println)
    }
    private fun complexSample(){
        val input = "((1 + 2) * 3 + 8 / (3 + 1) - 1) / 5 - 1"
        val tokenCursor = input.toTokenCursor()
        tokenCursor.reifyAll().forEach(::println)
    }
}
