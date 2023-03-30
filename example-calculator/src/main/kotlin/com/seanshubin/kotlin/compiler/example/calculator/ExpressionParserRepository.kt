package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.*

object ExpressionParserRepository {
    val number = TypeOfParser<Token>("number", Token.Number::class.java)
    val plus = ValueOfParser<Token>("plus", Token.Plus)
    val minus = ValueOfParser<Token>("minus", Token.Minus)
    val times = ValueOfParser<Token>("times", Token.Times)
    val divide = ValueOfParser<Token>("divide", Token.Divide)
    val openParen = ValueOfParser<Token>("open-paren", Token.OpenParen)
    val closeParen = ValueOfParser<Token>("close-paren", Token.CloseParen)
    val plusMinus = OneOfParser<Token>("plus-minus", "plus", "minus")
    val timesDivide = OneOfParser<Token>("times-divide", "times", "divide")
    val expressionInParenthesis = SequenceParser<Token>(
        "expression-in-parenthesis",
        "open-paren",
        "expression",
        "close-paren"
    )
    val factor = OneOrMoreInterleave<Token>(
        "factor",
        "number-or-expression-in-parenthesis",
        "times-divide"
    )
    val factorOrExpressionInParenthesis = OneOfParser<Token>(
        "factor-or-expression-in-parenthesis",
        "factor",
        "expression-in-parenthesis"
    )
    val numberOrExpressionInParenthesis = OneOfParser<Token>(
        "number-or-expression-in-parenthesis",
        "number",
        "expression-in-parenthesis"
    )
    val expression = OneOrMoreInterleave<Token>(
        "expression",
        "factor-or-expression-in-parenthesis",
        "plus-minus"
    )

    val map = listOf(
        number,
        plus,
        minus,
        times,
        divide,
        openParen,
        closeParen,
        expressionInParenthesis,
        factorOrExpressionInParenthesis,
        numberOrExpressionInParenthesis,
        factor,
        plusMinus,
        timesDivide,
        expression
    ).associateBy { it.name }
}
/*
expression: term | term + term | term − term
term:       factor | factor * factor | factor / factor
factor:     number | ( expression ) | + factor | − factor

    val number = TypeOfParser<Token>("number", Token.Number::class.java)
    val plus = ValueOfParser<Token>("plus", Token.Plus)
    val minus = ValueOfParser<Token>("minus", Token.Minus)
    val times = ValueOfParser<Token>("times", Token.Times)
    val divide = ValueOfParser<Token>("divide", Token.Divide)
    val openParen = ValueOfParser<Token>("open-paren", Token.OpenParen)
    val closeParen = ValueOfParser<Token>("close-paren", Token.CloseParen)
    val positive = SequenceParser<Token>("positive", plus, factor)
    val negative = SequenceParser<Token>("negative", minus, factor)
    val expressionInParenthesis = SequenceParser(
        "expression-in-parenthesis",
        openParen,
        expression,
        closeParen
    )
    val factor = OneOfParser<Token>(
        "factor",
        number,
        expressionInParenthesis,
        positive,
        negative
    )

    val multiplyFactors = SequenceParser<Token>(
        "multiply-factors",
        factor,
        times,
        factor
    )
    val divideFactors = SequenceParser<Token>(
        "divide-factors",
        factor,
        divide,
        factor
    )
    val term = OneOfParser<Token>(
        "term",
        multiplyFactors,
        divideFactors,
        factor
    )
    val addTerms = SequenceParser<Token>(
        "add-terms",
        term,
        plus,
        term
    )
    val subtractTerms = SequenceParser<Token>(
        "subtract-terms",
        term,
        minus,
        term
    )

    val expression = OneOfParser<Token>(
        "expression",
        addTerms,
        subtractTerms,
        term
    )

    val map = listOf(
        number,
        plus,
        minus,
        times,
        divide,
        openParen,
        closeParen,
        positive,
        negative,
        expressionInParenthesis,
        factor,
        multiplyFactors,
        divideFactors,
        term,
        addTerms,
        subtractTerms,
        expression
    ).associateBy { it.name }

 */