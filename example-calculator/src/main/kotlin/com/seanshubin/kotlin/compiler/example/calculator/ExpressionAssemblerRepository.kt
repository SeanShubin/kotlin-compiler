package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler

object ExpressionAssemblerRepository {
    val numberAssembler = ExpressionNumberAssembler("number")
//    val operatorNumberListAssembler = ExpressionOperatorNumberListAssembler("operator-number-list")
//    val operatorNumberAssembler = ExpressionOperatorNumberAssembler("operator-number")
//    val expressionAssembler = ExpressionAssembler("expression")
//    val operatorAssembler = ExpressionOperatorAssembler("operator")
    val plusAssembler = ExpressionOperatorAssembler("plus")
    val minusAssembler = ExpressionOperatorAssembler("minus")
    val timesAssembler = ExpressionOperatorAssembler("times")
    val divideAssembler = ExpressionOperatorAssembler("divide")
    val expressionAssembler = ExpressionAssembler("expression")
    val expressionInParenthesisAssembler = ExpressionInParenthesisAssembler("expression-in-parenthesis")
    val factorAssembler = ExpressionFactorAssembler("factor")
    val map = listOf<Assembler<Token, Expression>>(
        expressionAssembler,
        factorAssembler,
        expressionInParenthesisAssembler,
        numberAssembler,
        plusAssembler,
        minusAssembler,
        timesAssembler,
        divideAssembler
//        operatorNumberListAssembler,
//        expressionAssembler,
//        operatorNumberAssembler,
//        operatorAssembler
    ).associateBy { it.name }
}
