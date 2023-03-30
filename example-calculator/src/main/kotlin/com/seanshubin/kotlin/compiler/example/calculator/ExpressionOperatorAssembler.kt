package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionOperatorAssembler(override val name: String) : Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        tree as Tree.Leaf
        return when (tree.value) {
            Token.Plus -> Expression.PlusOperator
            Token.Minus -> Expression.MinusOperator
            Token.Times -> Expression.TimesOperator
            Token.Divide -> Expression.DivideOperator
            else -> throw RuntimeException("Operator not found '${tree.value}'")
        }
    }
}
