package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.*

object ExpressionParserRepository {
    val number = TypeOfParser<Token>("number", Token.Number::class.java)
    val operator = OneOfValueParser<Token>("operator", Token.Plus, Token.Minus)
    val operatorNumber = SequenceParser<Token>("operator-number", operator, number)
    val operatorNumberList = ZeroOrMoreParser<Token>("operator-number-list", operatorNumber)
    val expression = SequenceParser<Token>(
        "expression",
        number,
        operatorNumberList
    )
    val map = listOf(
        number,
        operator,
        operatorNumber,
        operatorNumberList,
        expression
    ).associateBy { it.name }
}
