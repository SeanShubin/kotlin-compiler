package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionFactorAssembler(override val name: String): Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        val parts = branchParts(lookupByName, tree)
        val firstNumber = parts[0] as Expression.Number
        var accumulator = firstNumber.x
        var index = 1
        while(index < parts.size){
            val operator = parts[index] as Expression.Operator
            val number = parts[index+1] as Expression.Number
            accumulator = operator.operation(accumulator, number.x)
            index += 2
        }
        return Expression.Number(accumulator)
    }
}
