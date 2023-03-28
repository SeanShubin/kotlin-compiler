package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class WhitespaceBlockAssembler(override val name: String) : Assembler<Char, Token> {
    override fun assemble(lookupByName: (String) -> Assembler<Char, Token>, tree: Tree<Char>): Token {
        val text = tree.flatten().joinToString("")
        return Token.WhitespaceBlock(text)
    }
}