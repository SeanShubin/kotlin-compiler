package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionOperatorAssembler(override val name: String): Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        tree as Tree.Leaf
        return when(tree.value) {
            Token.Plus -> Expression.Plus
            Token.Minus -> Expression.Minus
            else -> throw RuntimeException("expected ${Token.Plus} or ${Token.Minus}")
        }
    }
}