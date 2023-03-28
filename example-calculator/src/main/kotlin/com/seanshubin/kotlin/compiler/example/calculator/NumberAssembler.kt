package com.seanshubin.kotlin.compiler.example.calculator

import com.seanshubin.kotlin.compiler.domain.Assembler
import com.seanshubin.kotlin.compiler.domain.Tree

class NumberAssembler(override val name: String) : Assembler<Char, Token> {
    override fun assemble(lookupByName: (String) -> Assembler<Char, Token>, tree: Tree<Char>): Token {
        val value = tree.flatten().joinToString("").toInt()
        return Token.Number(value)
    }
}