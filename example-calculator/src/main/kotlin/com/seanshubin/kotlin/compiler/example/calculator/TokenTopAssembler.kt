package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.TopAssembler
import com.seanshubin.kotlin.compiler.domain.Tree

class TokenTopAssembler(
    private val assemblerMap: Map<String, Assembler<Char, Token>>
) : TopAssembler<Char, Token> {
    override fun assemble(tree: Tree<Char>): Token =
        assemblerMap.getValue(tree.name).assemble(tree)
}
