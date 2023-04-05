package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.*

object ExpressionParserRepository {
    val expression = OneOrMoreInterleave<Token>(
        "expression",
        "term",
        "term-operator"
    )
    val term = OneOrMoreInterleave<Token>(
        "term",
        "factor",
        "factor-operator"
    )
    val factor = OneOfParser<Token>(
        "factor",
        "number",
        "expression-in-parenthesis",
        "sign-factor"
    )
    val termOperator = OneOfValueParser<Token>(
        "term-operator",
        Token.Plus,
        Token.Minus
    )
    val factorOperator = OneOfValueParser<Token>(
        "factor-operator",
        Token.Times,
        Token.Divide
    )
    val number = TypeOfParser<Token>("number", Token.Number::class.java)
    val expressionInParenthesis = SequenceParser<Token>(
        "expression-in-parenthesis",
        "open-paren",
        "expression",
        "close-paren"
    )
    val openParen = ValueOfParser<Token>("open-paren", Token.OpenParen)
    val closeParen = ValueOfParser<Token>("close-paren", Token.CloseParen)
    val signFactor = SequenceParser<Token>(
        "sign-factor",
        "term-operator",
        "factor"
    )
    val map = listOf(
        expression,
        term,
        factor,
        number,
        expressionInParenthesis,
        openParen,
        closeParen,
        termOperator,
        factorOperator,
        signFactor
    ).associateBy { it.name }
}
