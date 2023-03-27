package com.seanshubin.kotlin.compiler.example.calculator

interface Token {
    object OpenParen : Token{
        override fun toString(): String = "OpenParen"
    }
    object CloseParen : Token {
        override fun toString(): String = "CloseParen"
    }
    data class WhitespaceBlock(val text: String) : Token{
        override fun toString(): String = "WhitespaceBlock('$text')"
    }
    object Plus : Token {
        override fun toString(): String = "Plus"
    }
    object Minus : Token {
        override fun toString(): String = "Minus"
    }
    object Times : Token {
        override fun toString(): String = "Times"
    }
    object Divide : Token {
        override fun toString(): String = "Divide"
    }
    data class Number(val x: Int) : Token {
        override fun toString(): String = "Number($x)"
    }
}
