package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionOperatorNumberListAssembler(override val name: String) : Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        val parts = branchParts(lookupByName, tree)
        val list = parts.map {
            it as Expression.OperatorNumber
            it
        }
        return Expression.OperatorNumberList(list)
    }
}
