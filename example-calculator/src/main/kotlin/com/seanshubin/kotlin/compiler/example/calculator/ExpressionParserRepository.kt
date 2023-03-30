package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.*

object ExpressionParserRepository {
    val expression = OneOfParser<Token>(
        "expression",
        "add",
        "subtract",
        "term"
    )
    val add = SequenceParser<Token>(
        "add",
        "term",
        "plus-operator",
        "expression"
    )
    val subtract = SequenceParser<Token>(
        "subtract",
        "term",
        "minus-operator",
        "expression"
    )
    val term = OneOfParser<Token>(
        "term",
        "multiply",
        "divide",
        "factor"
    )
    val plusOperator = ValueOfParser<Token>("plus-operator", Token.Plus)
    val multiply = SequenceParser<Token>(
        "multiply",
        "factor",
        "times-operator",
        "term"
    )
    val divide = SequenceParser<Token>(
        "divide",
        "factor",
        "divide-operator",
        "term"
    )
    val factor = OneOfParser<Token>(
        "factor",
        "number",
        "expression-in-parenthesis"
    )
    val timesOperator = ValueOfParser<Token>("times-operator", Token.Times)
    val number = TypeOfParser<Token>("number", Token.Number::class.java)
    val expressionInParenthesis = SequenceParser<Token>(
        "expression-in-parenthesis",
        "open-paren",
        "expression",
        "close-paren"
    )
    val openParen = ValueOfParser<Token>("open-paren", Token.OpenParen)
    val closeParen = ValueOfParser<Token>("close-paren", Token.CloseParen)
    val divideOperator = ValueOfParser<Token>("divide-operator", Token.Divide)
    val minusOperator =  ValueOfParser<Token>("minus-operator", Token.Minus)

    val map = listOf(
        expression,
        add,
        subtract,
        term,
        plusOperator,
        multiply,
        divide,
        factor,
        timesOperator,
        number,
        expressionInParenthesis,
        openParen,
        closeParen,
        divideOperator,
        minusOperator
    ).associateBy { it.name }
}
/*
expression: term | term + term | term − term
term:       factor | factor * factor | factor / factor
factor:     number | ( expression ) | + factor | − factor
 */