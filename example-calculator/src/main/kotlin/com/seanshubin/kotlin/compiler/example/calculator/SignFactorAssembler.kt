package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class SignFactorAssembler(override val name: String) : Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        val source = sourceAt(tree, 0)
        val construct = constructAt(lookupByName, tree, 1)
        return when(source){
            Token.Plus -> Expression.Positive(construct)
            Token.Minus -> Expression.Negative(construct)
            else -> throw RuntimeException("Invalid token $source")
        }
    }
}