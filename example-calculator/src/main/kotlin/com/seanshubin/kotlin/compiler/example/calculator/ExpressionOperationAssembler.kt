package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionOperationAssembler(override val name: String) : Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        val parts = branchParts(lookupByName, tree)
        val first = parts[0]
        val operator = parts[1]
        val second = parts[2]
        return when (operator) {
            Expression.PlusOperator -> Expression.Add(first, second)
            Expression.MinusOperator -> Expression.Subtract(first, second)
            Expression.TimesOperator -> Expression.Multiply(first, second)
            Expression.DivideOperator -> Expression.Divide(first, second)
            else -> throw RuntimeException("Operator not found '${operator}'")
        }
    }
}
