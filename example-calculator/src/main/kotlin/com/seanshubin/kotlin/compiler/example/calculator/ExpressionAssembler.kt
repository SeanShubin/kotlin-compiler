package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionAssembler(override val name: String) : Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression =
        assembleInterleaved(lookupByName, tree) { left, middle, right ->
            when (middle) {
                Token.Plus -> {
                    Expression.Add(left, right)
                }
                Token.Minus -> {
                    Expression.Subtract(left, right)
                }
                else -> {
                    throw RuntimeException("Invalid token $middle")
                }
            }
        }
}
