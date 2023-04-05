package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler

object ExpressionAssemblerRepository {
    val expressionAssembler = ExpressionAssembler("expression")
    val termAssembler = TermAssembler("term")
    val expressionInParenthesisAssembler = ExpressionInParenthesisAssembler("expression-in-parenthesis")
    val numberAssembler = ExpressionNumberAssembler("number")
    val signFactorAssembler = SignFactorAssembler("sign-factor")
    val map = listOf<Assembler<Token, Expression>>(
        expressionAssembler,
        termAssembler,
        expressionInParenthesisAssembler,
        numberAssembler,
        signFactorAssembler
    ).associateBy { it.name }
}
