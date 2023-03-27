package com.seanshubin.kotlin.compiler.example.calculator

interface Token {
    object OpenParen : Token
    object CloseParen : Token
    data class WhitespaceBlock(val text: String) : Token
    object Plus : Token
    object Minus : Token
    object Times : Token
    object Divide : Token
    data class Number(val x: Int) : Token
}
