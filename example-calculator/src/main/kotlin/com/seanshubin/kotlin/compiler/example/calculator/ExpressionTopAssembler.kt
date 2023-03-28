package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.TopAssembler
import com.seanshubin.kotlin.compiler.domain.Tree

class ExpressionTopAssembler(
    private val assemblerMap: Map<String, Assembler<Token, Expression>>
) : TopAssembler<Token, Expression> {
    override fun assemble(tree: Tree<Token>): Expression =
        assemblerMap.getValue(tree.name).assemble(assemblerMap::getValue, tree)
}
