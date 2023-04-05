package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionAssembler(override val name: String): Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        val constructs = interleaveBranchConstructedOuter(lookupByName, tree)
        val sources = interleaveBranchSourceInner(tree)
        var top = constructs[0]
        fun combine(left:Expression, middle:Token, right:Expression):Expression {
            return when (middle) {
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
        sources.indices.forEach{ sourceIndex ->
            val constructIndex = sourceIndex+1
            val left = top
            val middle = sources[sourceIndex]
            val right = constructs[constructIndex]
            top = combine(left, middle, right)
        }
        return top
    }
}