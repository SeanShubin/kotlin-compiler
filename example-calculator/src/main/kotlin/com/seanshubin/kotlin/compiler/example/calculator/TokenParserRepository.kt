package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.*

object TokenParserRepository {
    val digit = OneOfValueParser("digit", "0123456789".toList())
    val number = OneOrMoreParser("number", digit)
    val openParen = ValueOfParser("open-paren", '(')
    val closeParen = ValueOfParser("close-paren", ')')
    val plus = ValueOfParser("plus", '+')
    val minus = ValueOfParser("minus", '-')
    val times = ValueOfParser("times", '*')
    val divide = ValueOfParser("divide", '/')
    val whitespace = ValueOfParser<Char>("whitespace", ' ')
    val whitespaceBlock = OneOrMoreParser<Char>("whitespace-block", whitespace)
    val token: Parser<Char> = OneOfParser<Char>(
        "token",
        openParen,
        closeParen,
        times,
        divide,
        plus,
        minus,
        whitespaceBlock,
        number
    )
    val map = listOf(
        openParen,
        closeParen,
        plus,
        minus,
        times,
        divide,
        whitespace,
        whitespaceBlock,
        number,
        token
    ).associateBy { it.name }
}
