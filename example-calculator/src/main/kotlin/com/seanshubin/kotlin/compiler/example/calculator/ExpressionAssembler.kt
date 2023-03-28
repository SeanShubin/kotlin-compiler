package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionAssembler(override val name: String): Assembler<Token, Expression> {
    override fun assemble(lookupByName: (String) -> Assembler<Token, Expression>, tree: Tree<Token>): Expression {
        tree as Tree.Branch
        val parts = tree.list.map { lookupByName(it.name).assemble(lookupByName, it) }
        val number = parts[0] as Expression.Number
        val operatorNumberList = parts[1] as Expression.OperatorNumberList
        var result = number.x
        operatorNumberList.list.forEach{
            result = it.operator.operation(result, it.number.x)
        }
        return Expression.Number(result)
    }
}