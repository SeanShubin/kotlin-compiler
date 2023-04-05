package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler

object ExpressionAssemblerRepository {
    val expressionAssembler = ExpressionAssembler("expression")
    val termAssembler = TermAssembler("term")
    val subtractAssembler = ExpressionOperationAssembler("subtract")
    val divideAssembler = ExpressionOperationAssembler("divide")
    val expressionInParenthesisAssembler = ExpressionInParenthesisAssembler("expression-in-parenthesis")
    val addAssembler = ExpressionOperationAssembler("add")
    val multiplyAssembler = ExpressionOperationAssembler("multiply")
    val numberAssembler = ExpressionNumberAssembler("number")
    val plusOperatorAssembler = ExpressionOperatorAssembler("plus-operator")
    val minusOperatorAssembler = ExpressionOperatorAssembler("minus-operator")
    val timesOperatorAssembler = ExpressionOperatorAssembler("times-operator")
    val divideOperatorAssembler = ExpressionOperatorAssembler("divide-operator")
    val map = listOf<Assembler<Token, Expression>>(
        expressionAssembler,
        termAssembler,
        subtractAssembler,
        divideAssembler,
        expressionInParenthesisAssembler,
        addAssembler,
        multiplyAssembler,
        numberAssembler,
        plusOperatorAssembler,
        minusOperatorAssembler,
        timesOperatorAssembler,
        divideOperatorAssembler
    ).associateBy { it.name }
}
