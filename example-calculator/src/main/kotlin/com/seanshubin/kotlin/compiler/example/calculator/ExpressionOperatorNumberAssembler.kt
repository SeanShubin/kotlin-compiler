package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionOperatorNumberAssembler(override val name: String): Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        val parts = branchParts(lookupByName, tree)
        val operator = parts[0] as Expression.Operator
        val number = parts[1] as Expression.Number
        return Expression.OperatorNumber(operator, number)
    }
}
