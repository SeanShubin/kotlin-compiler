package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.example.calculator.TokenCompiler.toTokenCursor

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = "((1 + 2) * 3 + 8 / (3 + 1) - 1) / 5 - 1"
        val tokenCursor = input.toTokenCursor()
        tokenCursor.reifyAll().forEach(::println)
    }
}
