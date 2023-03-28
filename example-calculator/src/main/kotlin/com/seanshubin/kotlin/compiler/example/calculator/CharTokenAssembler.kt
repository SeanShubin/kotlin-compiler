package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class CharTokenAssembler(override val name: String, private val token: Token) : Assembler<Char, Token> {
    override fun assemble(lookupByName: (String) -> Assembler<Char, Token>, tree: Tree<Char>): Token {
        return token
    }
}