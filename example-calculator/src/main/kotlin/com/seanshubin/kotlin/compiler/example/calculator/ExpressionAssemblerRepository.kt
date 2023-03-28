package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler

object ExpressionAssemblerRepository {
    val numberAssembler = ExpressionNumberAssembler("number")
    val operatorNumberListAssembler = ExpressionOperatorNumberListAssembler("operator-number-list")
    val operatorNumberAssembler = ExpressionOperatorNumberAssembler("operator-number")
    val expressionAssembler = ExpressionAssembler("expression")
    val operatorAssembler = ExpressionOperatorAssembler("operator")
    val map = listOf<Assembler<Token, Expression>>(
        numberAssembler,
        operatorNumberListAssembler,
        expressionAssembler,
        operatorNumberAssembler,
        operatorAssembler
    ).associateBy { it.name }
}
