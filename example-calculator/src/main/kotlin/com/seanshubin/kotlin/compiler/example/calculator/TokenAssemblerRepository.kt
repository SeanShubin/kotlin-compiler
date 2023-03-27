package com.seanshubin.kotlin.compiler.example.calculator

object TokenAssemblerRepository {
    val openParen = CharTokenAssembler("open-paren", Token.OpenParen)
    val closeParen = CharTokenAssembler("close-paren", Token.CloseParen)
    val plus = CharTokenAssembler("plus", Token.Plus)
    val minus = CharTokenAssembler("minus", Token.Minus)
    val times = CharTokenAssembler("times", Token.Times)
    val divide = CharTokenAssembler("divide", Token.Divide)
    val whitespaceBlock = WhitespaceBlockAssembler("whitespace-block")
    val number = NumberAssembler("number")
    val map = listOf(
        openParen,
        closeParen,
        plus,
        minus,
        times,
        divide,
        whitespaceBlock,
        number
    ).associateBy { it.name }
}
