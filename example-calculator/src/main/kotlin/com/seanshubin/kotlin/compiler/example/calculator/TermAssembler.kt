package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class TermAssembler(override val name: String) : Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression =
        assembleInterleaved(lookupByName, tree) { left, middle, right ->
            when (middle) {
                Token.Times -> {
                    Expression.Multiply(left, right)
                }
                Token.Divide -> {
                    Expression.Divide(left, right)
                }
                else -> {
                    throw RuntimeException("Invalid token $middle")
                }
            }
        }
}
